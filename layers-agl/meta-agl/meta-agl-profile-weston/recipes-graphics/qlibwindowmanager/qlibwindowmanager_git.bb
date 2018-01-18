SUMMARY     = "A wrapper library of libwindowmanager for Qt Application in AGL"
SECTION     = "graphics"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

DEPENDS = "qtbase libwindowmanager"
RDEPENDS_${PN} = "libwindowmanager"

inherit qmake5

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/qlibwindowmanager.git;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "77460e797c950f6a2c742cc8604a58b5deb86616"
S = "${WORKDIR}/git"
