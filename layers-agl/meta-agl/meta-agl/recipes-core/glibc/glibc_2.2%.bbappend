FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_arm = " file://arm/local-arm-futex.diff "
