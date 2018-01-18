########################
# AGL DISTRO VARIABLES #
########################

# ARM 32bit 'medium' compiler (armv7thf instead of armv7ve)
# (only use for CPUs <= cortex-a9 / w/o virt extension - e.g. cortex-a8)
# DISTRO_FEATURES_append = " agl-medium-arm-compiler"


######################
# AGL USER VARIABLES #
######################

# Add additional IMAGE_FSTYPES here on-top of the default ext4.xz and tar.xz
#AGL_EXTRA_IMAGE_FSTYPES = ""

# Additional free disk space created in the image in Kbytes.
#IMAGE_ROOTFS_EXTRA_SPACE="524288"

