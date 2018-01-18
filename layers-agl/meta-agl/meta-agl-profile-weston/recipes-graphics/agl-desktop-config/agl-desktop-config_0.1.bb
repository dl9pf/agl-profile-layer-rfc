SUMMARY = "AGL desktop config"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += " \
    file://user-weston-term.service \
    file://user-weston-term.path \
"

do_install_append() {
    install -d ${D}${systemd_user_unitdir}
    install -m 0644 ${WORKDIR}/user-weston-term.service ${D}${systemd_user_unitdir}
    install -m 0644 ${WORKDIR}/user-weston-term.path ${D}${systemd_user_unitdir}

    install -d ${D}${systemd_user_unitdir}/default.target.wants
    ln -sf ${systemd_user_unitdir}/user-weston-term.path ${D}${libdir}/systemd/user/default.target.wants
}

FILES_${PN} += "${systemd_user_unitdir}/*"
FILES_${PN} += "${systemd_user_unitdir}/default.target.wants/default.target.wants"
