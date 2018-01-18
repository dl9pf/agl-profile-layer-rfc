FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://0001-fix-arm-trusted-firmware-build-for-gcc6.patch"

PACKAGE_ARCH = "${MACHINE_ARCH}"
