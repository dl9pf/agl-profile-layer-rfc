SUMMARY = "A very basic Wayland image with a terminal"

require agl-image-weston.inc

LICENSE = "MIT"

IMAGE_INSTALL_append = "\
    packagegroup-agl-image-weston \
    "

DISTRO_FEATURES_append = " agl-core-image-profile"
IMAGE_INSTALL_append = " agl-desktop-config"
