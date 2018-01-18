
# For running GStreamer on wayland (experimental).
#
# Uncoment below if want to use multimedia acceleration on wayland
#
IMAGE_INSTALL_append_porter = " \
    gstreamer1.0-plugins-bad-waylandsink \
    "

# Additional plugins to test multimedia acceleration (experimental)
#
# Uncoment below if want to use multimedia acceleration
#
IMAGE_INSTALL_append_porter = " \
    gstreamer1.0-plugins-base-videorate \
    gstreamer1.0-plugins-base-videotestsrc \
    gstreamer1.0-plugins-base-audiotestsrc \
    gstreamer1.0-plugins-base-alsa \
    gstreamer1.0-plugins-good-autodetect \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-videocrop \
    gstreamer1.0-plugins-bad-asfmux \
    gstreamer1.0-plugins-bad-fbdevsink \
    gstreamer1.0-plugins-bad-faad \
    gstreamer1.0-plugins-bad-videoparsersbad \
"
#    gstreamer1.0-plugins-good-video4linux2  (temp disabled)

# for graphics acceleration
#
# Uncoment below if want to use multimedia acceleration
#
IMAGE_INSTALL_append_porter = " \
    libegl libegl-dev libgbm-dev \
    gles-kernel-module gles-user-module \
    libdrm-tests \
   libdrm-kms \
    "

# for multimedia acceleration
#
# Uncoment below if want to use multimedia acceleration
#
IMAGE_INSTALL_append_porter = " \
    packagegroup-rcar-gen2-multimedia \
    packagegroup-lcb-oss-codecs \
    alsa-utils alsa-utils-config alsa-tools \
"

IMAGE_INSTALL_append_porter = " \
    gles-kernel-module \
    libegl \
    libgbm-dev \
    "

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

# TODO: When booting weston, someone try to load 'libgbm_kms.so' then
# fail to boot. It should be 'libgbm_kms.so.1'.
# Tentatively, it is to install 'libgbm-dev' pkg.
#IMAGE_INSTALL_append = "libgbm-dev"
# this is not recommended and should be done in the recipe requiring this module (gles-user-module)

LICENSE_FLAGS_WHITELIST = "commercial"

# Define the name of multimedia, dtv, graphic test, multimedia-test for bbmask
BB_MULTIMEDIA_KERNEL_MODULE = "fdpm-kernel-module|mmngr-kernel-module|mmngrbuf-kernel-module|s3ctl-kernel-module|uvcs-kernel-module|vspm-kernel-module"
BB_MULTIMEDIA_USER_MODULE = "fdpm-user-module|mmngr-user-module|mmngrbuf-user-module|omx-user-module|s3ctl-user-module|vspm-user-module|libmemcpy"
BB_MULTIMEDIA_TEST_MODULE = "fdpm-tp-user-module|mmngr-tp-user-module|mmngrbuf-tp-user-module|s3ctl-tp-user-module|vspm-tp-user-module"

BB_GST_PLUGINS = "meta-renesas/meta-rcar-gen2/recipes-multimedia/gstreamer"
MULTIMEDIA_BB = "${BB_MULTIMEDIA_KERNEL_MODULE}|${BB_MULTIMEDIA_USER_MODULE}|\
                 ${BB_MULTIMEDIA_TEST_MODULE}|${BB_GST_PLUGINS}"

# Enable Multimedia standard pkg
MACHINE_FEATURES_append = " multimedia"

# UnMask the multimedia kernel modules
#BBMASK .= "|${BB_MULTIMEDIA_KERNEL_MODULE}"

# UnMask the multimedia user modules
#BBMASK .= "|${BB_MULTIMEDIA_USER_MODULE}"

# Include static library in SDK
SDKIMAGE_FEATURES_append = " staticdev-pkgs"

# Mask the DTV pkgs
#MACHINE_FEATURES_append = " dtv"
BBMASK = "dtv-module|ssp-module|scu-module"

# Enable graphic pkgs
#BBMASK .= "|gles-kernel-module|gles-user-module"

# Mask the multimedia-test
BBMASK .= "|${BB_MULTIMEDIA_TEST_MODULE}"

# Mask the graphic test
BBMASK .= "|gles-test-module"

#DISTRO_FEATURES_append = " mm-test"
#DISTRO_FEATURES_append = " gles-test"

# Enable Gfx Pkgs
MACHINE_FEATURES_append = " sgx"
MULTI_PROVIDER_WHITELIST += "virtual/libgl virtual/egl virtual/libgles1 virtual/libgles2"

# for Wayland/Weston

PREFERRED_PROVIDER_virtual/libgles1 = ""
PREFERRED_PROVIDER_virtual/libgles2 = "gles-user-module"
PREFERRED_PROVIDER_virtual/egl = "libegl"
PREFERRED_PROVIDER_virtual/libgl = ""
PREFERRED_PROVIDER_virtual/mesa = "mesa"
PREFERRED_PROVIDER_libgbm = "libgbm"
PREFERRED_RPROVIDER_libgbm-dev = "libgbm"

# Setting for u-boot Wayland
UBOOT_MACHINE = "porter_vin_config"

# Addtional Configurations of Optional OMX modules

# OMX Media Component H263 Decoder Library
#DISTRO_FEATURES_append = " h263dec_lib"
# OMX Media Component VC-1 Decoder Library
#DISTRO_FEATURES_append = " vc1dec_lib"
# OMX Media Component MPEG-4 Decoder Library
#DISTRO_FEATURES_append = " mpeg4dec_lib"
# VCP3 Driver Adapted for Linux MPEG-2 Decoder Library
#DISTRO_FEATURES_append = " mpeg2dec_lib"
# DivX Decoder Library for Linux
#DISTRO_FEATURES_append = " divxdec_lib"
# Encoder Library for Linux
DISTRO_FEATURES_append = " h264avcenc_lib"

# Configurations of Optional Audio Decoders
# ARM 5.1ch aacPlus V2 Decoder for Linux
#DISTRO_FEATURES_append = " aacp2dec_lib"
# ARM MP3 Decoder for Linux
#DISTRO_FEATURES_append = " mp3dec_lib"
# ARM WMA Decoder for Linux
#DISTRO_FEATURES_append = " wmadec_lib"
# ARM 5.1ch Dolby Digital Decoder for Linux
#DISTRO_FEATURES_append = " ddddec_lib"
# ARM ALAC Decoder for Linux
#DISTRO_FEATURES_append = " alacdec_lib"
# ARM FLAC Decoder for Linux
#DISTRO_FEATURES_append = " flacdec_lib"
# ARM AAC-LC Decoder Library Sample for Linux
#DISTRO_FEATURES_append = " aaclcsdec_lib"
# ARM AAC Encoder for Linux
#DISTRO_FEATURES_append = " aacenc_lib"

# Configurations of Optional Audio Middlewares
# ARM 5.1ch aacPlus V2 Decode Middleware for Linux
#DISTRO_FEATURES_append = " armaccp2dec_mdw"
# ARM MP3 Decode Middleware for Linux
#DISTRO_FEATURES_append = " mp3dec_mdw"
# ARM WMA Decode Middleware for Linux
#DISTRO_FEATURES_append = " wmadec_mdw"
# ARM 5.1ch Dolby Digital Decode Middleware for Linux
#DISTRO_FEATURES_append = " ddddec_mdw"
# ARM ALAC Decode Middleware for Linux
#DISTRO_FEATURES_append = " alacdec_mdw"
# ARM FLAC Decode Middleware for Linux
#DISTRO_FEATURES_append = " flacdec_mdw"
# ARM AAC Decode Middleware Sample for Linux
#DISTRO_FEATURES_append = " aacsdec_mdw"
# ARM AAC Encode Middleware for Linux
#DISTRO_FEATURES_append = " aacenc_mdw"

# Build updatable image. Only takes effect when sota.bbclass is inherited
DISTRO_FEATURES_append = " sota"
