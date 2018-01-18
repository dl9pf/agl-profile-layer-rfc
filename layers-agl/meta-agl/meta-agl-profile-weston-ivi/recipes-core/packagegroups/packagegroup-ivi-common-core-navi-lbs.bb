SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Navigation and Location Based Services Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-navi-lbs \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
