FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG_WAYLAND = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"
PACKAGECONFIG_GL = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"
PACKAGECONFIG_append = " ${PACKAGECONFIG_WAYLAND} icu accessibility"
PACKAGECONFIG_append = " fontconfig"
PACKAGECONFIG_append = " sql-sqlite"

EXTRA_OECONF_append = ""

# '-qpa wayland-egl' set wayland-egl as default of platform plagins
PACKAGECONFIG[wayland]="-qpa wayland-egl -no-qpa-platform-guard"

# Temporarily fix bug due to binutils 2.28
QT_CONFIG_FLAGS_append = " -no-use-gold-linker"
