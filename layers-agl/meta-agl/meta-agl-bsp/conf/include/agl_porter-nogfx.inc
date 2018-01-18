MACHINE = "porter"

# For Bluetooth hardware support
BT_KERNEL_MODULES = " \
    kernel-module-btusb \
    kernel-module-bnep \
    kernel-module-rfcomm \
    kernel-module-rfkill \
    "

# Distribution-specific runtime components
IMAGE_INSTALL_append = " \
    ${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "${BT_KERNEL_MODULES}", "", d)} \
    "

# Setting for u-boot Wayland
UBOOT_MACHINE = "porter_vin_config"

PREFERRED_PROVIDER_virtual/libgles2 = "mesa"
PREFERRED_PROVIDER_virtual/egl = "mesa"

# Build updatable image. Only takes effect when sota.bbclass is inherited
DISTRO_FEATURES_append = " sota"

