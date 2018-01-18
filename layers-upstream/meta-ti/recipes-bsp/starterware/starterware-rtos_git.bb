require starterware.inc

DEPENDS = "gcc-arm-none-eabi-native"

require recipes-ti/includes/ti-paths.inc

PR = "${INC_PR}.0"

PARTNO_ti33x = "am335x"
PARTNO_ti43x = "am437x"

export TOOLCHAIN_PATH_A8 = "${GCC_ARM_NONE_TOOLCHAIN}"
export TOOLCHAIN_PATH_A9 = "${GCC_ARM_NONE_TOOLCHAIN}"

do_compile() {
    cd build
    ./release_${PARTNO}.sh
}

do_install() {
    install -d ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/starterware
    find -name "*.tar" -exec tar xf {} --no-same-owner -C ${D}${PDK_INSTALL_DIR_RECIPE}/packages/ti/starterware \;
}

FILES_${PN} += "${PDK_INSTALL_DIR_RECIPE}/packages"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} = "arch staticdev"
