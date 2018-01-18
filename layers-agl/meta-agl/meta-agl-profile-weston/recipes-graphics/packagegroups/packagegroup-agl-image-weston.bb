DESCRIPTION = "The minimal set of packages required for basic Wayland image"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-image-weston \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "weston weston-init weston-ini-conf weston-examples"
RDEPENDS_${PN} += "agl-login-manager"

RDEPENDS_${PN} += "\
    packagegroup-agl-image-minimal \
    "
