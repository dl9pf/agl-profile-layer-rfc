#!/bin/sh

################################################################################
#
# Init script to boot over network through NBD
#
# Contact: Stéphane Desneux <stephane.desneux@iot.bzh>
#
################################################################################

# banner generator: echo "AGL - Netboot" | figlet -f slant -w 80 -c
cat <<'EOF' >&2
________________________________________________________________________________
         ___   ________                _   __     __  __                __
        /   | / ____/ /               / | / /__  / /_/ /_  ____  ____  / /_
       / /| |/ / __/ /      ______   /  |/ / _ \/ __/ __ \/ __ \/ __ \/ __/
      / ___ / /_/ / /___   /_____/  / /|  /  __/ /_/ /_/ / /_/ / /_/ / /_
     /_/  |_\____/_____/           /_/ |_/\___/\__/_.___/\____/\____/\__/
________________________________________________________________________________
EOF

# global variables

SMACK=n
NBD_SERVER=
NBD_PORT=10809
NBD_DEV=/dev/nbd0
DEBUG=n

# -------------------------------------------

log_info() { echo "$0[$$]: $@" >&2; }
log_error() { echo "$0[$$]: ERROR $@" >&2; }

do_mount_fs() {
	log_info "mounting FS: $@"
	[[ -e /proc/filesystems ]] && { grep -q "$1" /proc/filesystems || { log_error "Unknown filesystem"; return 1; } }
	[[ -d "$2" ]] || mkdir -p "$2"
	[[ -e /proc/mounts ]] && { grep -q -e "^$1 $2 $1" /proc/mounts && { log_info "$2 ($1) already mounted"; return 0; } }
	mount -t "$1" "$1" "$2"
}

bail_out() {
	log_error "$@"
	check_debug "Reboot will occur after exiting this shell."
	log_info "Rebooting..."
	exec reboot -f
}

check_debug() {
	case $DEBUG in
		Y|y|yes|1|true)
			log_info "$@"
			/bin/sh -i
			;;
	esac
}

# -------------------------------------------

export PATH=/sbin:/usr/sbin:/bin:/usr/bin

log_info "starting initrd script"

do_mount_fs proc /proc
do_mount_fs sysfs /sys
do_mount_fs devtmpfs /dev
do_mount_fs devpts /dev/pts
do_mount_fs tmpfs /dev/shm
do_mount_fs tmpfs /tmp
do_mount_fs tmpfs /run

# parse kernel commandline to get NBD server
for x in $(cat /proc/cmdline); do
	case $x in
		nbd.server=*) NBD_SERVER=${x/*=/};;
		nbd.port=*) NBD_PORT=${x/*=/};;
		nbd.dev=*)  NBD_DEV=/dev/${x/*=/};;
		nbd.debug=*) DEBUG=${x/*=/};;
	esac
done

check_debug "Debug point 1. Exit to continue initrd script (mount NBD device)."

log_info "NBD parameters: device $NBD_DEV, server $NBD_SERVER:$NBD_PORT"

# check if smack is active (and if so, mount smackfs)
grep -q smackfs /proc/filesystems && {
	SMACK=y

	do_mount_fs smackfs /sys/fs/smackfs

	# adjust current label and network label
	echo System >/proc/self/attr/current
	echo System >/sys/fs/smackfs/ambient
}

# start nbd client
try=5
while :;do
	log_info "Starting NBD client"
	nbd-client $NBD_SERVER $NBD_PORT $NBD_DEV && { log_info "NBD client successfully started"; break; }
	log_info "NBD client failed"
	[[ $try -gt 0 ]] && { log_info "Retrying ($try trie(s) left)..."; sleep 3; try=$(( try - 1 )); continue; }

	bail_out "Unable to mount NBD device $NBD_DEV using server $NBD_SERVER:$NBD_PORT"
done

# mount NBD device
mkdir -p /sysroot
mount $NBD_DEV -o noatime /sysroot || bail_out "Unable to mount root NBD device"

# move mounted devices to new root
cd /sysroot
for x in dev proc sys tmp run; do
	log_info "Moving /$x to new rootfs"
	mount -o move /$x $x
done

# switch to new rootfs
log_info "Switching to new rootfs"
mkdir -p run/initramfs
pivot_root . run/initramfs || bail_out "pivot_root failed."

# workaround for connman (avoid bringing down the network interface used for booting, disable DNS proxy)
if [[ -f /lib/systemd/system/connman.service ]]; then
	log_info "Adjusting Connman configuration"
	iface=$(ip -o link show up | tr ':' ' ' | awk '{print $2}' | grep -v -e "^lo$" | head -1)
	sed -i "s|connmand -n\$|connmand -r -n -I $iface|g" /lib/systemd/system/connman.service
fi

# also use /proc/net/pnp to generate /etc/resolv.conf
grep -v bootserver /proc/net/pnp | sed 's/^domain/search/g' >/etc/resolv.conf

# finally, run systemd
check_debug "Debug point 2. Exit to continue initrd script (run systemd)."

log_info "Exec'ing systemd"
# banner generator: echo "AGL Booting . . ." | figlet -f slant -w 80 -c
cat <<'EOF' >&2
________________________________________________________________________________
      ___   ________       ____              __  _
     /   | / ____/ /      / __ )____  ____  / /_(_)___  ____ _
    / /| |/ / __/ /      / __  / __ \/ __ \/ __/ / __ \/ __ `/
   / ___ / /_/ / /___   / /_/ / /_/ / /_/ / /_/ / / / / /_/ /   _     _     _
  /_/  |_\____/_____/  /_____/\____/\____/\__/_/_/ /_/\__, /   (_)   (_)   (_)
_____________________________________________________/____/_____________________
EOF

exec /lib/systemd/systemd </dev/console >/dev/console 2>&1
bail_out

