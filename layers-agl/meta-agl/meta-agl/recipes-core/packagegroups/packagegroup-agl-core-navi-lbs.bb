DESCRIPTION = "The minimal set of packages for Navigation and Location Based Services Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-core-navi-lbs \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
