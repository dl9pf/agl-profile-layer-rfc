SUMMARY = "The packages of middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required by Speech Services Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-speech-services \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    "
