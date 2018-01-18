SUMMARY = "The packages of middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required by Security Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-security \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
