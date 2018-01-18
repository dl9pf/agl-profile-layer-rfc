python __anonymous() {
    features = d.getVar("MACHINE_FEATURES", 1)
    if not features:
        return
    if "mmip" not in features:
        raise bb.parse.SkipPackage('ipumm-fw does not apply to systems without the "mmip" flag in MACHINE_FEATURES')
}

DESCRIPTION = "Firmware for IPU for supporting Accelerated MM decode and encode"
LICENSE = "TI-TSPA"

LIC_FILES_CHKSUM = "file://MMIP-${PV}-Manifest.doc;md5=255f135205844dcbeab09b481ff9fb3a"

COMPATIBLE_MACHINE = "dra7xx"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = " libdce"

SRC_URI = "http://downloads.ti.com/infotainment/esd/jacinto6/processor-sdk-linux-automotive/03_02_00_03/exports/ipumm-dra7xx-evm-${PV}.tar.gz;protocol=http"

SRC_URI[md5sum] = "d5cf8c3be28f22cd94e97ab2b781a4df"
SRC_URI[sha256sum] = "b003a981cc97d834c81ec653dca9888c0e6c99500ee695a24f3221898f6afed4"

S = "${WORKDIR}/ipumm-dra7xx-evm-${PV}"

TARGET = "dra7-ipu2-fw.xem4"

do_install() {
        mkdir -p ${D}${base_libdir}/firmware
        cp ${S}/firmware/${TARGET} ${D}${base_libdir}/firmware/${TARGET}
}

FILES_${PN} += "${base_libdir}/firmware/${TARGET}"
