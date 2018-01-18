OSTREE_BOOTLOADER ?= "u-boot"

# DEFAULTTUNE for cortex-a9
DISTRO_FEATURES_append = " agl-medium-arm-compiler"


# Add the custom rpi-sdimg output format
AGL_EXTRA_IMAGE_FSTYPES  = "socfpga-sdimg.xz"

# Use the AGL default ext4.xz to generate the rpi-sdimg
SDIMG_ROOTFS_TYPE = "ext4.xz"
