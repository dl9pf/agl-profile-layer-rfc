SUMMARY = "A high-performance memory object caching system"
DESCRIPTION = "\
 memcached optimizes specific high-load serving applications that are designed \
 to take advantage of its versatile no-locking memory access system. Clients \
 are available in several different programming languages, to suit the needs \
 of the specific application. Traditionally this has been used in mod_perl \
 apps to avoid storing large chunks of data in Apache memory, and to share \
 this burden across several machines."
SECTION = "web"
HOMEPAGE = "http://memcached.org/"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://COPYING;md5=7e5ded7363d335e1bb18013ca08046ff"

inherit autotools

DEPENDS += "libevent"
RDEPENDS_${PN} += "perl perl-module-posix perl-module-autoloader \
    perl-module-tie-hash bash \
    "

SRC_URI = "http://www.memcached.org/files/${BP}.tar.gz \
           file://memcached-add-hugetlbfs-check.patch \
           "
SRC_URI[md5sum] = "1e028fbab7288911fcaa5ed2a21817fe"
SRC_URI[sha256sum] = "773b6bb20bf80223ca6a15d75f570fbab346ad11ec98595e5af5e33f54bd82d8"

# set the same COMPATIBLE_HOST as libhugetlbfs
COMPATIBLE_HOST = '(i.86|x86_64|powerpc|powerpc64|arm).*-linux'

python __anonymous () {
    endianness = d.getVar('SITEINFO_ENDIANNESS')
    if endianness == 'le':
        d.appendVar('EXTRA_OECONF', " ac_cv_c_endian=little")
    else:
        d.appendVar('EXTRA_OECONF', " ac_cv_c_endian=big")
}

PACKAGECONFIG ??= ""
PACKAGECONFIG[hugetlbfs] = "--enable-hugetlbfs, --disable-hugetlbfs, libhugetlbfs"

inherit update-rc.d

INITSCRIPT_NAME = "memcached"
INITSCRIPT_PARAMS = "defaults"

do_install_append() {
    install -D -m 755 ${S}/scripts/memcached-init ${D}${sysconfdir}/init.d/memcached
    mkdir -p ${D}/usr/share/memcached/scripts
    install -m 755 ${S}/scripts/memcached-tool ${D}/usr/share/memcached/scripts
    install -m 755 ${S}/scripts/start-memcached ${D}/usr/share/memcached/scripts
}
