SUMMARY = "libtar, tar manipulating library"
DESCRIPTION = "libtar is a library for manipulating POSIX tar files"
HOMEPAGE = "http://www.feep.net/libtar"
SECTION = "libs"
LICENSE = "NCSA"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=61cbac6719ae682ce6cd45b5c11e21af"

# Source and patches taken from debian check there for changes.
# Check http://repo.or.cz/w/libtar.git/ as upstream.
# More patches found here: https://github.com/tklauser/libtar
#
SRC_URI  = "http://httpredir.debian.org/debian/pool/main/libt/libtar/libtar_${PV}.orig.tar.gz"
SRC_URI += "file://no_static_buffers.patch"
SRC_URI += "file://no_maxpathlen.patch"
SRC_URI += "file://CVE-2013-4420.patch"
SRC_URI += "file://th_get_size-unsigned-int.patch"
SRC_URI += "file://oldgnu_prefix.patch"
SRC_URI += "file://testsuite.patch"
SRC_URI += "file://no_strip.patch"

SRC_URI[md5sum] = "6ced95ab3a4b33fbfe2dfb231d156cdb"
SRC_URI[sha256sum] = "50f24c857a7ef1cb092e6508758b86d06f1188508f897f3e6b40c573e8879109"

# tarball does not contain PV
S = "${WORKDIR}/${PN}"

CFLAGS_append  = " -DHAVE_STDARG_H"

RDEPENDS_${PN} += "zlib"

inherit autotools
