DESCRIPTION = "Thai language support routines"
LICENSE = "LGPL"
DEPENDS = "libdatrie libdatrie-native"

SRC_URI = "http://linux.thai.net/pub/thailinux/software/libthai/${PN}-${PV}.tar.xz"

inherit autotools pkgconfig

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI[md5sum] = "2b58148d73361316121d5384efeda9e6"
SRC_URI[sha256sum] = "5a107f5a4dc5433ec6deb744435a4d8dc89daec0a189b9c76a90648fda0ef7ed"
