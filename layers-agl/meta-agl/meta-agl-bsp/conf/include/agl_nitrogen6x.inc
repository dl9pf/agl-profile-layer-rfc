PREFERRED_PROVIDER_u-boot = "u-boot-imx6"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-imx6"
PREFERRED_PROVIDER_u-boot-mxsboot-native = "u-boot-imx6"

AGL_DEFAULT_IMAGE_FSTYPES = "sdcard.gz"

# prevent of build errors from missing bb files
BBMASK = "meta-boundary/recipes-(browser|mozilla)"

PREFERRED_VERSION_weston_mx6_forcevariable = "2.0.0"
