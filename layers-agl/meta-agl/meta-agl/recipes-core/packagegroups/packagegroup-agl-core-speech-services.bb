DESCRIPTION = "The minimal set of packages for Speech Services Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-core-speech-services \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
