RDEPENDS_${PN}_append_smack = " smack-userspace"
PACKAGE_WRITE_DEPS_append_smack = " smack-userspace-native"

do_install_append() {
    install -d ${D}/${sysconfdir}/skel/app-data
    install -d ${D}/${sysconfdir}/skel/.config
    install -m 0755 -d ${D}/var
    if [ -d ${D}/usr/local ]; then
        mv ${D}/usr/local ${D}/var
    else
        install -m 0755 -d ${D}/var/local
    fi
    ln -s ../../var/local ${D}/usr/local
}

do_install_append_smack () {
    install -d ${D}/${sysconfdir}/smack/accesses.d
    cat > ${D}/${sysconfdir}/smack/accesses.d/default-access-domains-no-user <<EOF
System User::App-Shared rwxat
System User::Home       rwxat
EOF
    chmod 0644 ${D}/${sysconfdir}/smack/accesses.d/default-access-domains-no-user
}

pkg_postinst_${PN}_append_smack() {
    chsmack -r -a 'User::Home' -t -D $D/${sysconfdir}/skel
    chsmack -a 'User::App-Shared' -D $D/${sysconfdir}/skel/app-data
    cp -rTf --preserve=all $D/${sysconfdir}/skel $D/${ROOT_HOME}
}

