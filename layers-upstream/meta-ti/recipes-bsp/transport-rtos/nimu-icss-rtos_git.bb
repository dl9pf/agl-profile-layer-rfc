SUMMARY = "RTOS driver for NIMU ICSS"

inherit ti-pdk
require transport.inc

PR = "${INC_PR}.0"

LIC_FILES_CHKSUM = "file://nimu_icssEth.h;beginline=1;endline=35;md5=fd897e67955a19ba88443d4bc2e7e18a"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|k2g|c66x|omapl1"

DEPENDS_append = " ti-ndk \
                   pruss-lld-rtos \
                   icss-emac-lld-rtos \
"

NIMU_ICSS_DESTSUFFIX = "git/ndk/nimu_icss"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

S = "${WORKDIR}/${NIMU_ICSS_DESTSUFFIX}"

export PDK_NIMU_ICSS_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

export NDK_INSTALL_PATH = "${NDK_INSTALL_DIR}"
XDCPATH_append = ";${NDK_INSTALL_DIR}/packages"
