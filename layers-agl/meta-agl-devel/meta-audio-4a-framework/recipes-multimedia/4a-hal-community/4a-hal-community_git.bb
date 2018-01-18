SUMMARY     = "4A - Community HALs"
DESCRIPTION = "HALs maintained by AGL Community for 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-community/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/src/4a-hal-community;protocol=https;branch=${AGL_BRANCH}"

SRCREV = "${AGL_APP_REVISION}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

#Select Here your HAL
EXTRA_OECMAKE += " -DHAL_JABRA=1"

FILES_${PN}-dev += "${INSTALL_PREFIX}/afb-aaaa/htdocs"
FILES_${PN} += "${INSTALL_PREFIX}/afb-aaaa"
FILES_${PN} += "${INSTALL_PREFIX}/lib"
