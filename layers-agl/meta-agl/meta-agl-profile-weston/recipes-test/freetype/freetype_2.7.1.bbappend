FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI =+ "${SOURCEFORGE_MIRROR}/freetype/ft2demos-${PV}.tar.bz2;name=ft2demos \
           file://0001-Makefile-dont-build-gfx-demos.patch;patchdir=../ft2demos-${PV} \
           file://0001-ft2demos-Makefile-Do-not-hardcode-libtool-path.patch;patchdir=../ft2demos-${PV} \
          "
SRC_URI[ft2demos.md5sum] = "5c10cb35bec755dbd3f7999e0f97aee3"
SRC_URI[ft2demos.sha256sum] = "d3f8a0d5a3f0d58701133458a8c1d3f97f658869f3c904b1fda447ed3b290ecd"

PACKAGES =+ "${PN}-demos"

B = "${S}"

do_compile_append () {

	oe_runmake -C ${WORKDIR}/ft2demos-${PV} TOP_DIR=${WORKDIR}/${BPN}-${PV}/
}

do_install_append () {
	install -d -m 0755 ${D}/${bindir}
	for x in ftbench ftdump ftlint ftvalid ttdebug; do 
		install -m 0755 ${WORKDIR}/ft2demos-${PV}/bin/.libs/$x ${D}/${bindir}
	done
}

FILES_${PN}-demos = "\
   ${bindir}/ftbench \
   ${bindir}/ftdump \
   ${bindir}/ftlint \
   ${bindir}/ftvalid \
   ${bindir}/ttdebug \
"
