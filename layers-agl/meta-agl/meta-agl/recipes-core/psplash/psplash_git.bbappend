FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://psplash-colors.h \
            file://psplash-start.service \
            file://psplash-quit.service \
            file://psplash-anim \
            "

SPLASH_IMAGES="file://psplash-poky-img.h;outsuffix=default"

inherit systemd

SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${PN}', '', d)}"
SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'psplash-start.service psplash-quit.service', '', d)}"

do_configure_append () {
	cd ${S}
	cp ../psplash-colors.h ./
}

do_install_append () {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
    fi
    install -m 755 ${WORKDIR}/psplash-anim ${D}/${bindir}
}
