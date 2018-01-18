FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

require recipes-kernel/linux/linux-agl.inc
require recipes-kernel/linux/linux-agl-4.9.inc

SRC_URI_append  = " file://namespace_fix.cfg \
	file://disable_ipv6.cfg \
	"
