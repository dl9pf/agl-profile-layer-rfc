# Enable Libvaapi for HW accelerated video support
#
IMAGE_INSTALL_append = " \
        libva \
        va-intel \
        gstreamer1.0-vaapi"

AGL_EXTRA_IMAGE_FSTYPES = "wic.xz wic.bmap wic.xz.sha256sum"
INITRD_LIVE = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE_LIVE}-${MACHINE}.ext4.gz"

# Modify this file under meta-agl-bsp/meta-intel/wic to change the
# kernel command line, including to change the screen resolution.
WKS_FILE = "systemd-intel-corei7-64-bootdisk.wks"
