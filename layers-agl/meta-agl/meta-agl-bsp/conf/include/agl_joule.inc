INTEL_MACHINE_SUBTYPE = "broxton-m"

IMAGE_INSTALL_append = " linux-firmware-iwlwifi-8000c linux-firmware-ibt"
IMAGE_INSTALL_append = " \
        libva \
        va-intel \
        gstreamer1.0-vaapi"
OSTREE_BOOTLOADER ?= "u-boot"

AGL_EXTRA_IMAGE_FSTYPES = "wic.xz wic.bmap wic.xz.sha256sum"
INITRD_LIVE = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE_LIVE}-${MACHINE}.ext4.gz"
# Modify this file under meta-agl-bsp/meta-intel/wic to change the
# kernel command line
WKS_FILE = "systemd-joule-bootdisk.wks"
