LICENSE_${PN}-ibt-license      	= "Firmware-ibt_firmware"
LICENSE_${PN}-ibt-11-5 		= "Firmware-ibt_firmware"
FILES_${PN}-ibt-license		= "/lib/firmware/LICENCE.ibt_firmware"
FILES_${PN}-ibt                 = "/lib/firmware/intel"

RDEPENDS_${PN}-ibt      += "${PN}-ibt-license"

PACKAGES =+ " ${PN}-ibt-license ${PN}-ibt "

PACKAGE_ARCH = "${MACHINE_ARCH}"
