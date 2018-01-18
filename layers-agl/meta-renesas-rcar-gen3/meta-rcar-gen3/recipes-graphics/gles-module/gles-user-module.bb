require include/gles-control.inc
require include/rcar-gen3-path-common.inc

DESCRIPTION = "PowerVR GPU user module"
LICENSE = "CLOSED"

PN = "gles-user-module"
PR = "r0"

COMPATIBLE_MACHINE = "(r8a7795|r8a7796)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/rogue"
GLES = "gsx"

SRC_URI_r8a7795 = "file://r8a7795_linux_gsx_binaries_gles3.tar.bz2"
SRC_URI_r8a7796 = "file://r8a7796_linux_gsx_binaries_gles3.tar.bz2"
SRC_URI_append = " \
    file://change-shell.patch \
    file://rc.pvr.service \
"

inherit update-rc.d systemd

INITSCRIPT_NAME = "pvrinit"
INITSCRIPT_PARAMS = "start 7 5 2 . stop 62 0 1 6 ."
SYSTEMD_SERVICE_${PN} = "rc.pvr.service"

do_populate_lic[noexec] = "1"
do_compile[noexec] = "1"

#The headers of gles-user-module need headers from virtual/mesa.
do_populate_sysroot[depends] += "virtual/mesa:do_populate_sysroot"

do_install() {
    # Install configuration files
    install -d ${D}/${sysconfdir}/init.d
    install -m 644 ${S}/${sysconfdir}/powervr.ini ${D}/${sysconfdir}
    install -m 755 ${S}/${sysconfdir}/init.d/rc.pvr ${D}/${sysconfdir}/init.d/
    install -d ${D}/${sysconfdir}/udev/rules.d
    install -m 644 ${S}/${sysconfdir}/udev/rules.d/72-pvr-seat.rules ${D}/${sysconfdir}/udev/rules.d/

    # Install header files
    install -d ${D}/${includedir}/EGL
    install -m 644 ${S}/${includedir}/EGL/*.h ${D}/${includedir}/EGL/
    install -d ${D}/${includedir}/GLES2
    install -m 644 ${S}/${includedir}/GLES2/*.h ${D}/${includedir}/GLES2/
    install -d ${D}/${includedir}/GLES3
    install -m 644 ${S}/${includedir}/GLES3/*.h ${D}/${includedir}/GLES3/
    install -d ${D}/${includedir}/KHR
    install -m 644 ${S}/${includedir}/KHR/khrplatform.h ${D}/${includedir}/KHR/khrplatform.h

    # Install pre-builded binaries
    install -d ${D}/${libdir}
    install -m 755 ${S}/${libdir}/*.so ${D}/${libdir}/
    install -d ${D}/${RENESAS_DATADIR}/bin
    install -m 755 ${S}/usr/local/bin/dlcsrv_REL ${D}/${RENESAS_DATADIR}/bin/dlcsrv_REL
    install -d ${D}/lib/firmware
    install -m 644 ${S}/lib/firmware/* ${D}/lib/firmware/

    # Install pkgconfig
    install -d ${D}/${libdir}/pkgconfig
    install -m 644 ${S}/${libdir}/pkgconfig/*.pc ${D}/${libdir}/pkgconfig/

    # Create symbolic link
    cd ${D}/${libdir}
    ln -s libEGL.so libEGL.so.1
    ln -s libGLESv2.so libGLESv2.so.2

    if [ "${USE_GLES_WAYLAND}" = "1" ]; then
        # Set the "WindowSystem" parameter for wayland
        if [ "${GLES}" = "gsx" ]; then
            sed -i -e "s/WindowSystem=libpvrDRM_WSEGL.so/WindowSystem=libpvrWAYLAND_WSEGL.so/g" \
                ${D}/${sysconfdir}/powervr.ini
        fi
    fi

    # Install systemd service
    if [ ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)} ]; then
        install -d ${D}/${systemd_system_unitdir}/
        install -m 644 ${WORKDIR}/rc.pvr.service ${D}/${systemd_system_unitdir}/
        install -d ${D}/${exec_prefix}/bin
        install -m 755 ${S}/${sysconfdir}/init.d/rc.pvr ${D}/${exec_prefix}/bin/pvrinit
    fi
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-debug \
"

FILES_${PN} = " \
    ${sysconfdir}/* \
    ${libdir}/* \
    /lib/firmware/rgx.fw* \
    ${RENESAS_DATADIR}/bin/* \
    ${exec_prefix}/bin/* \
"

FILES_${PN}-dev = " \
    ${includedir}/* \
    ${libdir}/pkgconfig/* \
"

PROVIDES = "virtual/libgles2"
FILES_${PN}-debug = " \
    ${RENESAS_DATADIR}/bin/dlcsrv_REL \
"

RPROVIDES_${PN} += " \
    ${GLES}-user-module \
    libgles2-mesa \
    libgles2-mesa-dev \
    libgles2 \
    libgles2-dev \
"

RDEPENDS_${PN} = " \
    kernel-module-gles \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'libgbm wayland-kms', '', d)} \
"

INSANE_SKIP_${PN} = "ldflags build-deps file-rdeps"
INSANE_SKIP_${PN}-dev = "ldflags build-deps file-rdeps"
INSANE_SKIP_${PN} += "arch"
INSANE_SKIP_${PN}-dev += "arch"
INSANE_SKIP_${PN}-dbg = "arch"

# Skip debug strip of do_populate_sysroot()
INHIBIT_SYSROOT_STRIP = "1"

# Skip debug split and strip of do_package()
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
