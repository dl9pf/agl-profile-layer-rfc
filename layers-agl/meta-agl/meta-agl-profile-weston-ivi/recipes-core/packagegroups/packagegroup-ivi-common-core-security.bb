SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Security Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-security \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
