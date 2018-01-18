# Maximum memory allowed to be assigned
GPU_MEM = "255"

ENABLE_CMA = "1"
CMA_LWM = "16"
CMA_HWM = "32"

KERNEL_IMAGETYPE = "Image"
KERNEL_DEVICETREE_append = " overlays/vc4-kms-v3d-overlay.dtb"

MACHINE_FEATURES += "vc4graphics"

# For libomxil
#LICENSE_FLAGS_WHITELIST = "commercial"

IMAGE_INSTALL_append = " kernel-modules linux-firmware "

# Psplash causes crash on first boot on RPi
IMAGE_FEATURES_remove = "splash"

# Add the custom rpi-sdimg output format
AGL_EXTRA_IMAGE_FSTYPES  = " rpi-sdimg.xz"

# Use the AGL default ext4.xz to generate the rpi-sdimg
SDIMG_ROOTFS_TYPE = "ext4.xz"
