SUMMARY = "The middlewares for AGL IVI profile"
DESCRIPTION = "A set of common packages required by Multimedia Subsystem"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-ivi-common-core-multimedia \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', '' , 'agl-audio-plugin', d)} \
    alsa-utils \
    pulseaudio-server \
    pulseaudio-misc \
    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', '\
        pulseaudio-module-bluetooth-discover \
        pulseaudio-module-bluetooth-policy \
        pulseaudio-module-bluez5-discover \
        pulseaudio-module-bluez5-device \
        pulseaudio-module-switch-on-connect \
        pulseaudio-module-loopback \
        ','', d)} \
    gstreamer1.0-meta-base \
    "
