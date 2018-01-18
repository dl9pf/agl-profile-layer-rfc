PACKAGES =+ "fontconfig-test"

do_install_append() {
	install -m 755 -d ${D}/${datadir}/fontconfig-test
	install -m 755 ${S}/test/run-test.sh ${D}/${datadir}/fontconfig-test/

	sed -i -e "s/FCLIST=\.\.\/fc\-list\/fc\-list/FCLIST=\/usr\/bin\/fc\-list/g" -e "s/FCCACHE=\.\.\/fc\-cache\/fc\-cache/FCCACHE=\/usr\/bin\/fc\-cache/g" ${D}/${datadir}/fontconfig-test/run-test.sh 

	for x in 4x6.pcf 8x16.pcf out.expected fonts.conf.in; do
		install -m 644 ${S}/test/$x ${D}/${datadir}/fontconfig-test/
	done
}

DEBIAN_NOAUTONAME_fontconfig-test = "1"
FILES_fontconfig-test = "${datadir}/fontconfig-test/*"


