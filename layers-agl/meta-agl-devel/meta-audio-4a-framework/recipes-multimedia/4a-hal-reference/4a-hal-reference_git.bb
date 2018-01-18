SUMMARY     = "4A - Reference HALs"
DESCRIPTION = "HALs used for Reference boards in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-reference/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/src/4a-hal-reference;protocol=https;branch=${AGL_BRANCH}"

SRCREV = "${AGL_APP_REVISION}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

EXTRA_OECMAKE += "-DHAL_USB_DEVICE=1"

# Select platform specific additional HAL(s)
EXTRA_OECMAKE_append_x86-64 = " -DHAL_INTEL_HDA=1"
# Mark as specific to M3
PACKAGE_ARCH_m3ulcb = "${MACHINE_ARCH}"
EXTRA_OECMAKE_append_m3ulcb = " -DHAL_RCAR-M3=1"

FILES_${PN}-dev += "${INSTALL_PREFIX}/afb-aaaa/htdocs"
FILES_${PN} += "${INSTALL_PREFIX}/afb-aaaa"
FILES_${PN} += "${INSTALL_PREFIX}/lib"
