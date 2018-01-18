DESCRIPTION = "Magic wand library"
HOMEPAGE = "http://docs.wand-py.org/en/0.4.3/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=170eafd687d4a2b950819cd5e067e6d5"

SRCNAME = "wand"

SRC_URI = "git://github.com/dahlia/wand.git;tag=${PV} \
           file://0001-wand-api.py-Change-search-order.patch"
S = "${WORKDIR}/git"

inherit setuptools

DEPENDS += " imagemagick-native"

# Tell python-wand where to look for our imagemagick and it must be
# one level up from where 'lib' resides.
export MAGICK_HOME="${STAGING_LIBDIR_NATIVE}/.."

BBCLASSEXTEND = "native"
