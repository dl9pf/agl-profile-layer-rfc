SUMMARY = "RTOS driver for Network Interface Management Unit (NIMU)"

inherit ti-pdk
require transport.inc

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://nimu_eth.h;beginline=1;endline=35;md5=52ba99291b03b91058e37115b66edb10"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|c66x|omapl1"

NIMU_DESTSUFFIX = "git/ndk/nimu"

S = "${WORKDIR}/${NIMU_DESTSUFFIX}"

DEPENDS_append = " ti-ndk osal-rtos"
DEPENDS_append_ti33x = " starterware-rtos emac-lld-rtos"
DEPENDS_append_ti43x = " starterware-rtos emac-lld-rtos"
DEPENDS_append_omap-a15 = " emac-lld-rtos"
DEPENDS_append_keystone = " qmss-lld-rtos cppi-lld-rtos"
DEPENDS_append_k2hk-evm = " pa-lld-rtos"
DEPENDS_append_k2e-evm = " pa-lld-rtos"
DEPENDS_append_k2l-evm = " pa-lld-rtos"
DEPENDS_append_k2g = " emac-lld-rtos"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

export PDK_NIMU_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export NDK_INSTALL_PATH = "${NDK_INSTALL_DIR}"
XDCPATH_append = ";${NDK_INSTALL_DIR}/packages"
