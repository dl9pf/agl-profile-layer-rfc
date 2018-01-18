SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "The set of packages required by Connectivity Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-ivi-connectivity \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    ofono \
    rtl-sdr \
    rygel \
    rygel-plugin-media-export \
    rygel-plugin-lms \
    "
