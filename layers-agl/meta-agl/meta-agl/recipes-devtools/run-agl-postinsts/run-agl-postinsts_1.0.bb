SUMMARY = "Runs AGL postinstall scripts on first boot of the target device"
SECTION = "devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://run-agl-postinsts \
           file://run-agl-postinsts.service"

S = "${WORKDIR}"

inherit allarch systemd

SYSTEMD_SERVICE_${PN} = "run-agl-postinsts.service"

SYSTEMD_SERVICE_AFTER ?= "dbus.service cynara.service"

SYSTEMD_SERVICE_BEFORE ?= "systemd-user-sessions.service"

do_configure() {
    :
}

do_compile () {
    :
}

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${WORKDIR}/run-agl-postinsts ${D}${sbindir}/

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/run-agl-postinsts.service ${D}${systemd_unitdir}/system/

    sed -i -e 's:#SYSCONFDIR#:${sysconfdir}:g' \
               -e 's:#SBINDIR#:${sbindir}:g' \
               -e 's:#BASE_BINDIR#:${base_bindir}:g' \
               -e 's:#LOCALSTATEDIR#:${localstatedir}:g' \
               ${D}${sbindir}/run-agl-postinsts \
               ${D}${systemd_unitdir}/system/run-agl-postinsts.service
 
    sed -i -e 's:#SYSTEMD_SERVICE_AFTER#:${SYSTEMD_SERVICE_AFTER}:g' \
               -e 's:#SYSTEMD_SERVICE_BEFORE#:${SYSTEMD_SERVICE_BEFORE}:g' \
               ${D}${systemd_unitdir}/system/run-agl-postinsts.service
}

