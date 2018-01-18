SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of packagegroups which contain common packages required by AGL Distribution"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-ivi-common-core-automotive \
    packagegroup-ivi-common-core-connectivity \
    packagegroup-ivi-common-core-graphics \
    packagegroup-ivi-common-core-multimedia \
    packagegroup-ivi-common-core-navi-lbs \
    packagegroup-ivi-common-core-os-commonlibs \
    packagegroup-ivi-common-core-speech-services \
    packagegroup-ivi-common-core-security \
    packagegroup-ivi-common-core-kernel \
    "
