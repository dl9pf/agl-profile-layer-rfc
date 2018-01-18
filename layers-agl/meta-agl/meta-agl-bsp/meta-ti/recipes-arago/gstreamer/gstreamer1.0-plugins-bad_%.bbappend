FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG = "faad"

# gstreamer is now also included on Keystone, be mindful of any Graphics dependencies
PACKAGECONFIG_append_omap-a15 = " ${@bb.utils.contains('DISTRO_FEATURES','wayland','wayland','',d)}"
PACKAGECONFIG_append_ti43x = " ${@bb.utils.contains('DISTRO_FEATURES','wayland','wayland','',d)}"
PACKAGECONFIG_append_ti33x = " ${@bb.utils.contains('DISTRO_FEATURES','wayland','wayland','',d)}"

DEPENDS_append_omap-a15 = " \
    libdrm \
"

DEPENDS_append_ti43x = " \
    libdrm \
"

DEPENDS_append_ti33x = " \
    libdrm \
"

############## F I X M E #####################
# FIXME pyro
SRC_URI_append = " \
    file://0001-gstdrmallocator-Add-DRM-allocator-support.patch \
"
DISABLED_SRC_URI_append_ti43x = " \
    file://0002-parsers-Pick-previos-bug-fixes-on-different-parsers.patch \
    file://0003-gstkmssink-Add-support-for-KMS-based-sink.patch \
    file://0004-gstwaylandsink-Add-DRM-support-on-waylandsink.patch \
    file://0002-kmssink-remove-DCE-dependencies.patch \
    file://0003-kmssink-add-YUYV-support.patch \
    file://0001-gstwaylandsink-add-input-format-I420-support.patch \
"

# FIXME pyro
DISABLED_SRC_URI_append_ti33x = " \
    file://0001-gstwaylandsink-Add-mouse-drag-and-drop-support.patch \
"

# FIXME pyro
DISABLED_SRC_URI_append_omap-a15 = " \
    file://0001-gstdrmallocator-Add-DRM-allocator-support.patch \
    file://0002-parsers-Pick-previos-bug-fixes-on-different-parsers.patch \
    file://0003-gstkmssink-Add-support-for-KMS-based-sink.patch \
    file://0004-gstwaylandsink-Add-DRM-support-on-waylandsink.patch \
    file://0002-kmssink-remove-DCE-dependencies.patch \
    file://0003-kmssink-add-YUYV-support.patch \
    file://0001-gstwaylandsink-add-input-format-I420-support.patch \
    file://0005-gstwaylandsink-Implement-callbacks-for-version-5-of-.patch \
"
################ F I X M E ####################

PACKAGE_ARCH = "${MACHINE_ARCH}"

PR = "r4"
