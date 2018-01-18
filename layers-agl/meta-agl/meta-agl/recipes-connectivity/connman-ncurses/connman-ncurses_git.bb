SUMMARY = "A simple ncurses interface for connman"
DESCRIPTION = "A simple ncurses interface for connman"
HOMEPAGE = "https://gitlab.com/iotbzh/connman-json-client"

SECTION = "console/network"

DEPENDS = "dbus ncurses connman json-c"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8c16666ae6c159876a0ba63099614381"

SRC_URI = "git://gitlab.com/iotbzh/connman-json-client.git;protocol=https;rev=ad4261a2f87c914667b1f6841c913f6b08d373e2"

inherit autotools pkgconfig

EXTRA_AUTORECONF += " -i"
EXTRA_OECONF += " --disable-optimization --enable-debug"

S = "${WORKDIR}/git"

do_install () {
    install -dm755 ${D}${bindir}
    install -Dm755 connman_ncurses ${D}${bindir}
}

FILES_${PN} = "${bindir}/connman_ncurses"
