FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://force-type-conversion.patch \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
