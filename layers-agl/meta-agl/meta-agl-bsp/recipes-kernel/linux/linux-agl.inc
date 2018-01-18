FILESEXTRAPATHS_prepend := "${THISDIR}/linux:"

DEPENDS += "kern-tools-native"

# returns all the elements from the src uri that are .cfg files
def find_cfgs(d):
    sources=src_patches(d, True)
    sources_list=[]
    for s in sources:
        if s.endswith('.cfg'):
            sources_list.append(s)

    return sources_list

do_configure_append () {
    [ ! -f .config ] && cp -a ${WORKDIR}/defconfig .config
    merge_config.sh -m .config ${@" ".join(find_cfgs(d))}
    yes '' | make oldconfig
}

# Extra configuration options for the AGL kernel
SRC_URI_append = "\
            file://can-bus.cfg \
            file://usb.cfg \
            file://uvc.cfg \
            file://joystick.cfg \
            file://fanotify.cfg \
            file://uinput.cfg \
            file://hid.cfg \
            file://drm.cfg \
            file://btusb.cfg \
            file://usbaudio.cfg \
            file://hciattach.cfg \
            "

KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/can-bus.cfg"
# Enable support for usb video class for usb camera devices
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/uvc.cfg"
# Enable support for joystick devices
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/joystick.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/fanotify.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/uinput.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/hid.cfg"
# Enable DRM support for graphics
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/drm.cfg"
# Enable Bluetooth USB devices
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/btusb.cfg"
# Enable USB audio devices
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/usbaudio.cfg"
# For new qemu
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/hciattach.cfg"

# Enable required features for the agl-netboot feature
SRC_URI_append_netboot = " file://nbd.cfg \
	file://ramdisk.cfg"
KERNEL_CONFIG_FRAGMENTS_append_netboot = " ${WORKDIR}/nbd.cfg ${WORKDIR}/ramdisk.cfg"

# Enable support for TP-Link TL-W722N USB Wifi adapter
SRC_URI_append = " file://ath9k_htc.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/ath9k_htc.cfg"

# Enable support for RTLSDR
SRC_URI_append = " file://rtl_sdr.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/rtl_sdr.cfg"

# Additional drivers for virtual machines
# OVERRIDES save us some c'n'p below ...
OVERRIDES_prepend_qemux86 = "virtualmachine:"
OVERRIDES_prepend_qemux86-64 = "virtualmachine:"
SRC_URI_append_virtualmachine = " file://vbox-vmware-sata.cfg"
KERNEL_CONFIG_FRAGMENTS_append_virtualmachine = " ${WORKDIR}/vbox-vmware-sata.cfg"

# Enable support for smack
KERNEL_CONFIG_FRAGMENTS_append_smack = "\
       ${WORKDIR}/audit.cfg \
       ${WORKDIR}/smack.cfg \
       ${WORKDIR}/smack-default-lsm.cfg \
"
