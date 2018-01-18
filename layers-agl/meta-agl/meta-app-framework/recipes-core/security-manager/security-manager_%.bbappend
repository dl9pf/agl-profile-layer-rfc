FILESEXTRAPATHS_prepend := "${THISDIR}/security-manager:"

PACKAGE_WRITE_DEPS_append_smack = " smack-userspace-native"

SRC_URI += " file://0001-Adapt-rules-to-AGL.patch \
	     file://init-security-manager-db.service \
	     file://init-security-manager-db.sh \
             file://0001-Fix-gcc6-build.patch \
             file://0001-Fix-Cmake-conf-for-gcc6-build.patch \
"

FILES_${PN}_append = "${bindir}/init-security-manager-db.sh \
		      ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_unitdir}/system/init-security-manager-db.service', '', d)} \
"

do_install_append () {
	install -p -D ${WORKDIR}/init-security-manager-db.sh ${D}${bindir}/init-security-manager-db.sh
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		mkdir -p ${D}${systemd_unitdir}/system
		mkdir -p ${D}${sysconfdir}/systemd/system/default.target.wants
		install -m 644 -p -D ${WORKDIR}/init-security-manager-db.service ${D}${systemd_unitdir}/system/init-security-manager-db.service
		ln -sf ${systemd_unitdir}/system/init-security-manager-db.service ${D}${sysconfdir}/systemd/system/default.target.wants
	fi
}
