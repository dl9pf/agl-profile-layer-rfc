FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Disable everything but the media-export plugin, add the lms plugin
PACKAGECONFIG = "media-export lms"
PACKAGECONFIG[lms] = "--enable-lms-plugin,--disable-lms-plugin,sqlite3"

# LightMediaScanner plugin patches
SRC_URI += "file://0001-Add-LightMediaScanner-plugin.patch \
            file://0002-lms-add-C-source-files.patch \
            file://0001-Fix-missing-link-to-unistring-for-lms-plugin.patch \
            file://rygel.service \
            "

inherit systemd

do_install_append() {
       # Install rygel systemd service
       if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
              install -m 644 -p -D ${WORKDIR}/rygel.service ${D}${systemd_user_unitdir}/rygel.service

              # Execute these manually on behalf of systemctl script (from systemd-systemctl-native.bb)
              # because it does not support systemd's user mode.
              # However, systemctl --global should be checked
              #mkdir -p ${D}/etc/systemd/user/default.target.wants/
              #ln -sf ${systemd_user_unitdir}/rygel.service ${D}/etc/systemd/user/dbus-org.gnome.Rygel1.service
              #ln -sf ${systemd_user_unitdir}/rygel.service ${D}/etc/systemd/user/default.target.wants/rygel.service
       fi
}

FILES_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/rygel.service', '', d)} \
    "
