SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required by Automotive Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-automotive \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
