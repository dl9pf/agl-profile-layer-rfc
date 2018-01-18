# Currently only works with fbdev backend
# and only one default output

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = "file://hdmi-a-1-270.cfg"
SRC_URI += "file://fbdev.cfg"

do_configure() {
    sed -i -e 's/drm-backend/fbdev-backend/' ${WORKDIR}/core.cfg
}
