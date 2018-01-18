SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Kernel Subysystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-kernel \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
