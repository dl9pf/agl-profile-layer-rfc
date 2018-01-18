# Caution: Hand-merge needed when original recipes are updated
#
# Here we only have selected lines from the following (and
# their included) files
#
# fslc-wayland.conf
# fsl-image-multimedia-full.bb
#

# fslc-wayland.conf

#
# Followings were intentionally removed from the original recipe
# to make our set simpler
# 
# gtk+3-demo clutter-1.0-examples
#

IMAGE_INSTALL_append = " \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-imx-tools-audio \
    weston weston-init weston-examples \
"

# fsl-image-multimedia-full.bb

IMAGE_INSTALL_append = " \
    packagegroup-fsl-gstreamer1.0-full \
"

# fix build error

SECURITY_CFLAGS_pn-devil = "${SECURITY_NO_PIE_CFLAGS}"

# since krogoth, u-boot-fslc does not seem to handle imx6qsabreauto correctly

PREFERRED_PROVIDER_u-boot = "u-boot-imx"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-imx"
PREFERRED_PROVIDER_u-boot-mxsboot-native = "u-boot-imx"

# force generate sdcard image also

AGL_DEFAULT_IMAGE_FSTYPES = "sdcard.xz"
AGL_DEFAULT_INITRAMFS_FSTYPES := ""

PREFERRED_VERSION_weston_mx6_forcevariable = "2.0.0"
