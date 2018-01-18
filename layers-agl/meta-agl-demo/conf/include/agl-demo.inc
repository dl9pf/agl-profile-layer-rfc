IMAGE_FEATURES =+ "debug-tweaks"

DISTRO_FEATURES_append = " webruntime "
DISTRO_FEATURES_append = " automount "

IMAGE_INSTALL_append = " screen mc "

DISTRO_FEATURES_append = " agl-hmi-framework"
PREFERRED_PROVIDER_virtual/libhomescreen = "libhomescreen-2017"
PREFERRED_RPROVIDER_virtual/navigation = "navigation"
