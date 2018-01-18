SUMMARY = "Startup script and systemd unit file for the Weston Wayland compositor"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRC_URI = "file://core.cfg \
           file://ivishell.cfg \
           file://id-agent.cfg \
           file://shell.cfg \
           file://hdmi-a-1-270.cfg \
          "

S = "${WORKDIR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile() {
    # Put all of our cfg files together.
    rm -f ${WORKDIR}/weston.ini
    for F in ${WORKDIR}/*.cfg; do
        cat $F >> ${WORKDIR}/weston.ini
        echo >> ${WORKDIR}/weston.ini
    done
    sed -i -e '$ d' ${WORKDIR}/weston.ini
}

do_install_append() {
    WESTON_INI_CONFIG=${sysconfdir}/xdg/weston
    install -d ${D}${WESTON_INI_CONFIG}
    install -m 0644 ${WORKDIR}/weston.ini ${D}${WESTON_INI_CONFIG}/weston.ini
}
