FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://default.pa.4a"

do_install_append () {
    cp ${WORKDIR}/default.pa.4a ${D}${sysconfdir}/pulse/default.pa
}
