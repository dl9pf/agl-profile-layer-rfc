SUMMARY     = "Low level CAN service"
DESCRIPTION = "AGL Service application for read and decode CAN messages"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/low-level-can-service"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/low-level-can-service;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "d8d990528ea9746b21788f97be3fdbcc392c5d96"

PV = "5.0+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

