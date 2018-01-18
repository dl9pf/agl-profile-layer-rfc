DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics"
HOMEPAGE = "http://dast.nlanr.net/Projects/Iperf/"
SECTION = "console/network"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=e8478eae9f479e39bc34975193360298"

SRC_URI = " https://iperf.fr/download/source/${BP}-source.tar.gz \
          "

SRC_URI[md5sum] = "1bb3a1d98b1973aee6e8f171933c0f61"
SRC_URI[sha256sum] = "a5350777b191e910334d3a107b5e5219b72ffa393da4186da1e0a4552aeeded6"

S = "${WORKDIR}/${BP}"

inherit autotools pkgconfig

EXTRA_OECONF = "--exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"
