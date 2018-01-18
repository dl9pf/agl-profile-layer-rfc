FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://dsi.cfg"

PACKAGE_ARCH = "${MACHINE_ARCH}"
