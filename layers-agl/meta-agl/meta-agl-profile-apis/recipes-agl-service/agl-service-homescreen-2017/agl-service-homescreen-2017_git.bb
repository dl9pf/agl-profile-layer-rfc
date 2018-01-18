SUMMARY     = "Homescreen binding and client library for application"
DESCRIPTION = "Homescreen 2017 is the binding library"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-homescreen-2017"
SECTION     = "HMI"
LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

DEPENDS = "dbus glib-2.0 af-binder json-c"

inherit cmake aglwgt

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-homescreen-2017;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "321f08ed8d0a4d3b1f4bd486e8a4240f4d3443ee"
S = "${WORKDIR}/git"
