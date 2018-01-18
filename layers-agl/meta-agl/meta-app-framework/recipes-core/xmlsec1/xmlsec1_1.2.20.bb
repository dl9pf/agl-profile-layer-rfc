inherit autotools pkgconfig

SUMMARY = "Library providing support for "XML Signature" and "XML Encryption" standards"
DESCRIPTION = "XML Security Library is a C library based on LibXML2  and OpenSSL. \ 
The library was created with a goal to support major XML security \
standards "XML Digital Signature" and "XML Encryption". \
"

HOMEPAGE = "https://www.aleksey.com/xmlsec"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Copyright;md5=32e47e213c77c55d2c666351d7ce16b5"

SRC_URI = "git://git.gnome.org/xmlsec;branch=master;protocol=git"
SRCREV = "84c8281cf927b1cdcc38f343f61c3aa448a5a10f"

SECTION = "base"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "openssl libxml2"

# choice is made to use openssl only and to not use xslt
# nss would be a valuable choice
EXTRA_OECONF = "\
	--disable-crypto-dl \
	--disable-apps-crypto-dl \
	--enable-shared \
	--disable-static \
	--without-gnutls \
	--without-gcrypt \
	--without-nss \
	--without-libxslt \
"

do_install_append() {
	# discarding this optional file is good for AGL
	rm ${D}${libdir}/xmlsec1Conf.sh
}


