FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# OVERRIDES save us some c'n'p below ...
OVERRIDES_prepend_qemux86 = "virtualmachine:"
OVERRIDES_prepend_qemux86-64 = "virtualmachine:"

# Switch to the Virtual section that we have when a valid DRM device is found
SRC_URI_remove_virtualmachine = "file://hdmi-a-1-270.cfg"
SRC_URI_append_virtualmachine = " file://virtual.cfg"

PACKAGE_ARCH = "${MACHINE_ARCH}"
