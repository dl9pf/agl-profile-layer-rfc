DESCRIPTION = "The minimal set of packages for Multimedia Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-core-multimedia \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
