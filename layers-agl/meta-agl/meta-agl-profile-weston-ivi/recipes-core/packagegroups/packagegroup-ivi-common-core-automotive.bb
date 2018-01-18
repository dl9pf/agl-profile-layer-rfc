SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Automotive Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-automotive \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += " "
