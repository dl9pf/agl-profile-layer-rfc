SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Operating System and Common libraries Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-os-commonlibs \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    procps \
    "
