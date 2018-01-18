SUMMARY = "Sound Manager package groups"
DESCRIPTION = "AGL sound manager package group based on Genivi Audio Manager"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-audio-soundmanager \
    "

RDEPENDS_${PN} += "\
    agl-service-audio-soundmanager \
    pulseaudio-config-soundmanager \
    audiomanager-plugins-config-soundmanager \
    libsoundmanager \
"

PROVIDES_${PN} += "virtual/audio-framework"
