SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Graphics Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-graphics \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    weston weston-init weston-ini-conf weston-examples \
    wayland-ivi-extension \
    "
