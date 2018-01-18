FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit agl-graphical

WESTONSTART ??= "/usr/bin/weston ${WESTONARGS}"
WESTONSTART_append = " ${@bb.utils.contains("IMAGE_FEATURES", "debug-tweaks", " --log=${DISPLAY_XDG_RUNTIME_DIR}/weston.log", "",d)}"

SRC_URI += " \
    file://weston_tmpfiles.conf \
    file://weston.service.add \
"

do_install_append() {
    sed -i "/\[Unit\]/aConflicts=getty@tty${WESTONTTY}.service" \
           ${D}${systemd_system_unitdir}/weston.service

    sed -i "/\[Service\]/r ${S}/weston.service.add" \
           ${D}${systemd_system_unitdir}/weston.service

    sed -e 's,User=root,User=${WESTONUSER},g' \
        -e 's,ExecStart=.*,ExecStart=${WESTONSTART},g' \
        -e 's,@WESTONTTY@,${WESTONTTY},g' \
        -e 's,@XDG_RUNTIME_DIR@,${DISPLAY_XDG_RUNTIME_DIR},g' \
        -i ${D}${systemd_system_unitdir}/weston.service

    # Add a rule to ensure the 'display' user has permissions to
    # open the graphics device
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/udev/rules.d
    cat >${D}${sysconfdir}/udev/rules.d/zz-dri.rules <<'EOF'
SUBSYSTEM=="drm", MODE="0660", GROUP="${WESTONGROUP}", SECLABEL{smack}="*", TAG+="systemd", ENV{SYSTEMD_WANTS}="weston.service"
EOF

    # user 'display' must own /dev/tty${WESTONTTY} for weston to start correctly
    cat >${D}${sysconfdir}/udev/rules.d/zz-tty.rules <<'EOF'
SUBSYSTEM=="tty", KERNEL=="tty${WESTONTTY}", OWNER="${WESTONUSER}", SECLABEL{smack}="^", TAG+="systemd", ENV{SYSTEMD_WANTS}="weston.service"
EOF

    # user 'display' must also be able to access /dev/input/*
    cat >${D}${sysconfdir}/udev/rules.d/zz-input.rules <<'EOF'
SUBSYSTEM=="input", MODE="0660", GROUP="input", SECLABEL{smack}="^", TAG+="systemd", ENV{SYSTEMD_WANTS}="weston.service"
EOF

    # user 'display' must also be able to access /dev/media*, etc.
    cat >${D}${sysconfdir}/udev/rules.d/zz-remote-display.rules <<'EOF'
SUBSYSTEM=="media", MODE="0660", GROUP="display", SECLABEL{smack}="*", TAG+="systemd", ENV{SYSTEMD_WANTS}="weston.service"
SUBSYSTEM=="video4linux", MODE="0660", GROUP="display", SECLABEL{smack}="*", TAG+="systemd", ENV{SYSTEMD_WANTS}="weston.service"
KERNEL=="uvcs", SUBSYSTEM=="misc", MODE="0660", GROUP="display", SECLABEL{smack}="*"
KERNEL=="rgnmm", SUBSYSTEM=="misc", MODE="0660", GROUP="display", SECLABEL{smack}="*"
EOF

    install -d ${D}${sysconfdir}/tmpfiles.d
    install -Dm755 ${WORKDIR}/weston_tmpfiles.conf ${D}/${libdir}/tmpfiles.d/weston.conf

    sed -e 's,@WESTONUSER@,${WESTONUSER},g' \
        -e 's,@WESTONGROUP@,${WESTONGROUP},g' \
        -i ${D}/${libdir}/tmpfiles.d/weston.conf
}

do_install_append_imx() {

    install -d ${D}${sysconfdir}/udev/rules.d
    cat >>${D}${sysconfdir}/udev/rules.d/zz-dri.rules <<'EOF'
SUBSYSTEM=="gpu_class", MODE="0660", GROUP="${WESTONGROUP}", SECLABEL{smack}="*"
EOF

}

FILES_${PN} += "${libdir}/tmpfiles.d/*.conf"


