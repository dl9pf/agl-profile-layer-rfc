SUMMARY = "big.LITTLE scheduler"
HOMEPAGE = "https://github.com/BayLibre/bLsched"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b0df75b2edb80ee98b732ee0514a265c"

SRC_URI = "git://github.com/baylibre/blsched.git \
	   file://blsched.service \
          "

SRCREV = "b6bf199bc2def486857fb7003a6cb07708ed685e"

S = "${WORKDIR}/git"

inherit autotools

COMPATIBLE_MACHINE ="((h|m)3ulcb|salvator-x)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DAEMONARGS_r8a7795 = "-b 0 -b 1 -b 2 -b 3"
DAEMONARGS_r8a7796 = "-b 0 -b 1"

RDEPENDS_${PN} += " blsched"

inherit systemd

SYSTEMD_SERVICE_${PN} = "blsched.service"

do_install_append () {
	# Install the systemd init file
	install -d ${D}${systemd_unitdir}/system
	install -c -m 0644 ${WORKDIR}/blsched.service ${D}${systemd_unitdir}/system

	sed -i -e 's,@SBINDIR@,${sbindir},g' \
		-e 's,@ARGS@,${DAEMONARGS},g' \
		${D}${systemd_unitdir}/system/*.service
}
