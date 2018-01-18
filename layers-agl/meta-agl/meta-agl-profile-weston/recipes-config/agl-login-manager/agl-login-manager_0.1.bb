SUMMARY = "AGL Login manager"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit agl-graphical

SRC_URI += " \
    file://user-config.service \
    file://user-config.path \
"

LOGIN_USER ??="1001 1002"

RDEPENDS_${PN} += "af-main"

do_install_append() {

    install -d ${D}${systemd_user_unitdir}/default.target.wants
    install -m 0644 ${WORKDIR}/user-config.service ${D}${systemd_user_unitdir}
    install -m 0644 ${WORKDIR}/user-config.path ${D}${systemd_user_unitdir}

    sed -e 's,@DISPLAY_XDG_RUNTIME_DIR@,${DISPLAY_XDG_RUNTIME_DIR},g' \
        -i ${D}${systemd_user_unitdir}/user-config.service
    sed -e 's,@DISPLAY_XDG_RUNTIME_DIR@,${DISPLAY_XDG_RUNTIME_DIR},g' \
        -i ${D}${systemd_user_unitdir}/user-config.path

    ln -s ../user-config.path ${D}${systemd_user_unitdir}/default.target.wants/user-config.path

    install -d ${D}${systemd_system_unitdir}/multi-user.target.wants/

    for AGL_USER in ${LOGIN_USER};do
        ln -s ../afm-user-session@.service ${D}${systemd_system_unitdir}/multi-user.target.wants/afm-user-session@${AGL_USER}.service
    done
}

FILES_${PN} += "${systemd_user_unitdir} ${systemd_system_unitdir}"
