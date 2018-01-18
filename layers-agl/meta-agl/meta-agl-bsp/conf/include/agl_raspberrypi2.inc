# Maximum memory allowed to be assigned
GPU_MEM = "255"

ENABLE_CMA = "1"
CMA_LWM = "16"
CMA_HWM = "32"

CORE_IMAGE_EXTRA_INSTALL += "wayland weston"

MULTI_PROVIDER_WHITELIST += "virtual/libgl virtual/egl virtual/libgles1 virtual/libgles2"

KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE_append = " overlays/vc4-kms-v3d-overlay.dtb"
PREFERRED_VERSION_mesa = "12.%"

PREFERRED_PROVIDER_virtual/egl = "mesa"
PREFERRED_PROVIDER_virtual/libgles2 = "mesa"
PREFERRED_PROVIDER_virtual/libgl = "mesa"
PREFERRED_PROVIDER_virtual/mesa = "mesa"
PREFERRED_PROVIDER_jpeg = "jpeg"

MACHINE_FEATURES += "vc4graphics"

# For libomxil
#LICENSE_FLAGS_WHITELIST = "commercial"

IMAGE_INSTALL_append = " kernel-modules linux-firmware "

# Psplash causes crash on first boot on RPi
IMAGE_FEATURES_remove = "splash"

# Build updatable image. Only takes effect when sota.bbclass is inherited
DISTRO_FEATURES_append = " sota"

# Create WIC images to write to SD cards.
WKS_FILE = "sdimage-raspberrypi.wks"
AGL_EXTRA_IMAGE_FSTYPES = "wic.xz wic.bmap wic.xz.sha256sum"
IMAGE_BOOT_FILES = "bcm2835-bootfiles/* \
                 ${@make_dtb_boot_files(d)} \
                 ${@bb.utils.contains('KERNEL_IMAGETYPE', 'uImage', \
                    '${KERNEL_IMAGETYPE} u-boot.bin;${SDIMG_KERNELIMAGE} boot.scr', \
                    '${KERNEL_IMAGETYPE};${SDIMG_KERNELIMAGE}', d)} \
                 "
do_image_wic[depends] += " \
    bcm2835-bootfiles:do_deploy \
    ${@bb.utils.contains('KERNEL_IMAGETYPE', 'uImage', 'u-boot:do_deploy', '',d)} \
    "
