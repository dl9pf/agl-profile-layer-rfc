SUMMARY     = "Launcher of XDG application on AGL HMI Framework (2017)"
DESCRIPTION = "The command 'runxdg' is a launcher to execute XDG application \
               on AGL HMI Framework which using wayland-ivi-extension"
HOMEPAGE    = "https://git.automotivelinux.org/staging/xdg-launcher"
LICENSE     = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS = "json-c wayland wayland-ivi-extension virtual/libhomescreen libwindowmanager"

inherit cmake

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/staging/xdg-launcher;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AGL_APP_REVISION}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"
