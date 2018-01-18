
# Configurations to run on VirtualBox/VMWare
#
# To get wide screen than default, there are a selection of resolutions
# available:
#
#APPEND += "uvesafb.mode_option=1024x768-32"
APPEND += "uvesafb.mode_option=1280x1024-32"
#APPEND += "uvesafb.mode_option=1600x1200-32"
#
# To avoid corrupt boot screen by systemd message, you can use serial
# console separated from VGA console or disable all boot messages by
# kernel command line.
#
# Configuration for serial console
APPEND += "console=ttyS0,115200n8"
#
# All boot message will be off
APPEND += "quiet"

# U-Boot support for SOTA
PREFERRED_PROVIDER_virtual/bootloader_sota = "u-boot-ota"
UBOOT_MACHINE_sota = "qemu-x86_defconfig"
OSTREE_BOOTLOADER ?= "u-boot"
OSTREE_KERNEL_ARGS ?= "ramdisk_size=16384 rw rootfstype=ext4 rootwait rootdelay=2 ostree_root=/dev/hda"
