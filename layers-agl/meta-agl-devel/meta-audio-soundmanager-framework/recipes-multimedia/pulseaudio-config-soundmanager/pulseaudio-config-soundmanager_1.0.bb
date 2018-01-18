SUMMARY = "PulseAudio configuration which doesn't load module-router"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://soundmanager.pa"

do_install () {
    install -d ${D}${sysconfdir}/pulse/default.d
    install -m 0644 ${WORKDIR}/soundmanager.pa ${D}${sysconfdir}/pulse/default.d/
}

PROVIDES_${PN} += "virtual/pulseaudio-config"
