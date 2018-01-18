SUMMARY = "The packages of middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required by Navigation and Location Based Services Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-navi-lbs \
    "

RDEPENDS_${PN} += "\
    gpsd \
    geoclue \
    "
