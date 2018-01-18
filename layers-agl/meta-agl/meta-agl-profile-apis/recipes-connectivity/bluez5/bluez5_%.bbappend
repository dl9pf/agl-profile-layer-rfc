FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI_append = " file://bluetooth.conf \
                   file://cve-2017-1000250.patch"

do_install_append() {
    install -m 0644 ${WORKDIR}/bluetooth.conf ${D}${sysconfdir}/dbus-1/system.d/bluetooth.conf
}
