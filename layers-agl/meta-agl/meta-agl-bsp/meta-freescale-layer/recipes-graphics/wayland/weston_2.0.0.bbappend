FILESEXTRAPATHS_prepend := "${THISDIR}/weston-2.0.0:"

SRC_URI_append_imxgpu3d = " \
    file://0001-libweston-Restore-EGL-support-for-the-fbdev-backend.patch     \
    file://0002-MGS-2352-ccc-Add-GPU-VIV-support-for-weston-2.0.patch     \
    file://0006-Link-compositor-to-egl.patch                               \
"

# The 'egl' configuration of weston requires gles support, and consideration
# must be taken for the different SoC capabilities:
# - For SoCs with 3d support, imx-gpu-viv provides hardware-accelerated
#   egl and gles, so weston egl configuration is enabled.
# - For SoCs with VG2D, like i.MX 6SoloLite, imx-gpu-viv provides
#   hardware-accelerated egl but does not provide a compatible software
#   version of gles, so weston egl configuration is disabled.
# - For SoCs with no GPU, mesa provides software implementations of egl
#   and gles, so weston egl configuration is enabled.
PACKAGECONFIG_IMX_TO_APPEND = ""
PACKAGECONFIG_IMX_TO_APPEND_imxgpu3d = "cairo-glesv2"
PACKAGECONFIG_IMX_TO_REMOVE = ""
PACKAGECONFIG_IMX_TO_REMOVE_imxgpu2d = "egl"
PACKAGECONFIG_IMX_TO_REMOVE_imxgpu3d = ""

PACKAGECONFIG_append = " ${PACKAGECONFIG_IMX_TO_APPEND}"
PACKAGECONFIG_remove = " ${PACKAGECONFIG_IMX_TO_REMOVE}"

EXTRA_OECONF_IMX_COMMON = " \
    --disable-libunwind \
    --disable-xwayland-test \
    WESTON_NATIVE_BACKEND=fbdev-backend.so \
"
EXTRA_OECONF_IMX          = ""
EXTRA_OECONF_IMX_imxpxp   = "${EXTRA_OECONF_IMX_COMMON}"
EXTRA_OECONF_IMX_imxgpu2d = "${EXTRA_OECONF_IMX_COMMON}"

EXTRA_OECONF_append = " ${EXTRA_OECONF_IMX}"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
