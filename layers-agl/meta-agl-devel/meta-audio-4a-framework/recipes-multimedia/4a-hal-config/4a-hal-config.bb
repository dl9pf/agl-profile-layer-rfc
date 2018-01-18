SUMMARY     = "4A - HAL Configuration"
DESCRIPTION = "Configuration files for HALs used in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-reference/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://asound.conf.template \
           file://ahl-config.json.template \
           "

PV = "0.1"

RPROVIDES_${PN} += "VIRTUAL-RUNTIME_alsa-state"

PACKAGE_ARCH = "${MACHINE_ARCH}"

AUDIO_DEV_NAME ?= "Speakers"
AUDIO_DEV_NAME_ID ?= "0"

AUDIO_DEV_NAME_m3ulcb = "M3-Speakers"
AUDIO_DEV_NAME_ID_m3ulcb = "rsnddai0ak4613h"

inherit afb-system

do_install () {
    install -d ${D}/${sysconfdir}
    install -m 0755 ${WORKDIR}/asound.conf.template ${D}/${sysconfdir}/asound.conf
    sed -i "s|@AUDIO_DEV_NAME@|${AUDIO_DEV_NAME}|g" ${D}/${sysconfdir}/asound.conf
    sed -i "s|@AUDIO_DEV_NAME_ID@|${AUDIO_DEV_NAME_ID}|g" ${D}/${sysconfdir}/asound.conf
    sed -i "s|@INSTALL_PREFIX@|${INSTALL_PREFIX}|g" ${D}/${sysconfdir}/asound.conf

    install -d "${D}${prefix}/agl-service-audio-4a"
    install -m 0755 ${WORKDIR}/ahl-config.json.template  "${D}${prefix}/agl-service-audio-4a/ahl-agl-service-audio-4a-config.json"
    sed -i "s|@AUDIO_DEV_NAME_ID@|${AUDIO_DEV_NAME_ID}|g" "${D}${prefix}/agl-service-audio-4a/ahl-agl-service-audio-4a-config.json"
}

FILES_${PN} += "${sysconfdir}/asound.conf"
FILES_${PN} += "${prefix}/agl-service-audio-4a/ahl-agl-service-audio-4a-config.json"
