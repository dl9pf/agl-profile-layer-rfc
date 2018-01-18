SUMMARY = "AGL Audio packages"
DESCRIPTION = "The set of packages required by the AGL Audio"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-audio \
    "

RDEPENDS_${PN} += "\
    4a-alsa-core \
    4a-hal-reference \
    agl-service-audio-4a \
    VIRTUAL-RUNTIME_alsa-state \
    agl-service-unicens \
    4a-hal-unicens \
"
