require af-main_${PV}.inc 

# NOTE: using libcap-native and setcap in install doesn't work
# NOTE: maybe setting afm_name to agl-framework is cleaner but has implications
# NOTE: there is a hack of security for using groups and dbus (to be checked)
# NOTE: using ZIP programs creates directories with mode 777 (very bad)

inherit cmake pkgconfig useradd systemd
BBCLASSEXTEND = "native"

SECTION = "base"

DEPENDS = "openssl libxml2 xmlsec1 systemd libzip json-c systemd security-manager af-binder"
DEPENDS_class-native = "openssl libxml2 xmlsec1 libzip json-c"
RDEPENDS_${PN}_class-target += "af-binder-tools"

PACKAGE_WRITE_DEPS_append_smack = " smack-userspace-native libcap-native"

EXTRA_OECMAKE_class-native  = "\
	-DUSE_LIBZIP=1 \
	-DUSE_SIMULATION=1 \
	-DUSE_SDK=1 \
	-Dafm_name=${afm_name} \
	-Dafm_confdir=${afm_confdir} \
	-Dafm_datadir=${afm_datadir} \
"

EXTRA_OECMAKE = "\
	-DUSE_LIBZIP=1 \
	-DUSE_SIMULATION=0 \
	-DUSE_SDK=0 \
	-Dafm_name=${afm_name} \
	-Dafm_confdir=${afm_confdir} \
	-Dafm_datadir=${afm_datadir} \
	-Dsystemd_units_root=${systemd_units_root} \
	-DUNITDIR_USER=${systemd_user_unitdir} \
	-DUNITDIR_SYSTEM=${systemd_system_unitdir} \
"

EXTRA_OECMAKE_append_agl-devel = " -DAGL_DEVEL=1"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-g ${afm_name} -d ${afm_datadir} -r ${afm_name}"
GROUPADD_PARAM_${PN} = "-r ${afm_name}"

FILES_${PN} += "\
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/afm-user-daemon.service', '', d)} \
"
RDEPENDS_${PN}_append_smack = " smack-userspace bash"
DEPENDS_append_smack = " smack-userspace-native"

# short hacks here
SRC_URI += "\
	file://Hack-to-allow-the-debugging.patch \
"

do_install_append_class-target() {
    install -d ${D}${bindir}
    install -d -m 0775 ${D}${systemd_units_root}/system
    install -d -m 0775 "${D}${systemd_units_root}/system/afm-user-session@.target.wants"
    install -d -m 0775 ${D}${systemd_units_root}/user
    install -d -m 0775 ${D}${systemd_units_root}/user/default.target.wants
    install -d -m 0775 ${D}${systemd_units_root}/user/sockets.target.wants
    install -d ${D}${afm_datadir}/applications
    install -d ${D}${afm_datadir}/icons
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d -m 0755 ${D}${systemd_user_unitdir}/default.target.wants
        ln -s ../afm-user-daemon.service ${D}${systemd_user_unitdir}/default.target.wants/afm-user-daemon.service
        install -d -m 0755 ${D}${systemd_system_unitdir}/default.target.wants
        install -d -m 0755 ${D}${systemd_system_unitdir}/sockets.target.wants
        ln -sf ../afm-system-daemon.service ${D}${systemd_system_unitdir}/default.target.wants/afm-system-daemon.service
        ln -sf ../afm-system-daemon.socket ${D}${systemd_system_unitdir}/sockets.target.wants/afm-system-daemon.socket
    fi
    echo "QT_WAYLAND_SHELL_INTEGRATION=ivi-shell" > ${D}${afm_confdir}/unit.env.d/qt-for-ivi-shell
}

do_install_append_porter() {
    echo "LD_PRELOAD=/usr/lib/libEGL.so" > ${D}${afm_confdir}/unit.env.d/preload-libEGL
}

pkg_postinst_${PN}() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        chgrp ${afm_name} $D${systemd_units_root}/system
        chgrp ${afm_name} $D${systemd_units_root}/system/afm-user-session@.target.wants
        chgrp ${afm_name} $D${systemd_units_root}/user/default.target.wants
        chgrp ${afm_name} $D${systemd_units_root}/user/sockets.target.wants
    fi
    chown ${afm_name}:${afm_name} $D${afm_datadir}
    chown ${afm_name}:${afm_name} $D${afm_datadir}/applications
    chown ${afm_name}:${afm_name} $D${afm_datadir}/icons
}

pkg_postinst_${PN}_append_smack() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        chsmack -a 'System::Shared' -t $D${systemd_units_root}/system
        chsmack -a 'System::Shared' -t $D${systemd_units_root}/system/afm-user-session@.target.wants
        chsmack -a 'System::Shared' -t $D${systemd_units_root}/user/default.target.wants
        chsmack -a 'System::Shared' -t $D${systemd_units_root}/user/sockets.target.wants
    fi
    chsmack -a 'System::Shared' -t $D${afm_datadir}
    chsmack -a 'System::Shared' -t $D${afm_datadir}/applications
    chsmack -a 'System::Shared' -t $D${afm_datadir}/icons
}
FILES_${PN} += "${systemd_units_root}/* ${systemd_system_unitdir} ${systemd_user_unitdir}"

PACKAGES =+ "${PN}-binding ${PN}-binding-dbg"
FILES_${PN}-binding = " ${afb_binding_dir}/afm-main-binding.so "
FILES_${PN}-binding-dbg = " ${afb_binding_dir}/.debug/afm-main-binding.so "

PACKAGES =+ "${PN}-tools ${PN}-tools-dbg"
FILES_${PN}-tools = "${bindir}/wgtpkg-*"
FILES_${PN}-tools-dbg = "${bindir}/.debug/wgtpkg-*"
