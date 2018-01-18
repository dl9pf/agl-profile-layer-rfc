DESCRIPTION = "The minimal set of packages required by AGL"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-image-minimal \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    packagegroup-core-boot-agl \
    "


RDEPENDS_${PN} += "\
    packagegroup-agl-core-automotive \
    packagegroup-agl-core-connectivity \
    packagegroup-agl-core-graphics \
    packagegroup-agl-core-multimedia \
    packagegroup-agl-core-navi-lbs \
    packagegroup-agl-core-os-commonlibs \
    packagegroup-agl-core-speech-services \
    packagegroup-agl-core-security \
    packagegroup-agl-core-kernel \
    "
