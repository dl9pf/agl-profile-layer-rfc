#
# No base recipe
#

DESCRIPTION = "datrie is an implementation of double-array structure \
for representing trie, as proposed by Junichi Aoe."
HOMEPAGE = "http://linux.thai.net/projects/libthai"

PR = "r0"

inherit autotools pkgconfig

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "http://linux.thai.net/pub/thailinux/software/libthai/${BPN}-${PV}.tar.xz"

SRC_URI[md5sum] = "687624f374e748de81b01399736bb735"
SRC_URI[sha256sum] = "d980713e7ad8e19cf3b54ee0173782b94332af8e7e4e936dde01d2cd72e1da1d"


# Add more packages
PACKAGES =+ "${PN}1-bin"

FILES_${PN}1-bin += "${bindir}/*"

BBCLASSEXTEND += "native"

