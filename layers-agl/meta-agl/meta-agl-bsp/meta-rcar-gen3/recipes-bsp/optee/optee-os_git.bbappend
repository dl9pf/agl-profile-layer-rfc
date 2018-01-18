FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
inherit pythonnative
DEPENDS += " python-pycrypto-native"

#Need for gcc 6.2
CFLAGS += " -fno-strict-aliasing -Wno-unused-variable -Wno-shift-negative-value"

SRC_URI_append = " \
    file://default_bold.c \
    file://default_bold.h \
    file://default_regular.c \
    file://default_regular.h \
    file://0001-Remove-the-file-generation-and-directly-copy-the-fil.patch \
    "


do_compile_prepend() {
    GENDIRECTORY=${S}/lib/libutee/tui/fonts/amble/
    cp ${WORKDIR}/default_bold.c ${GENDIRECTORY}
    cp ${WORKDIR}/default_bold.h ${GENDIRECTORY}
    cp ${WORKDIR}/default_regular.c ${GENDIRECTORY}
    cp ${WORKDIR}/default_regular.h ${GENDIRECTORY}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
