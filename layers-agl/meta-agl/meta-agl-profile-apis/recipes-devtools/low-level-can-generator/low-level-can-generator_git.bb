SUMMARY     = "Low level CAN generator"
DESCRIPTION = "Generator used to customize low level CAN service with customs signals"
SECTION     = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig
BBCLASSEXTEND = "nativesdk"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/src/low-level-can-generator;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "92f95384ce4b4a198b1fea93272201421f7b9a39"

PV = "4.0+git${SRCPV}"
S  = "${WORKDIR}/git"

