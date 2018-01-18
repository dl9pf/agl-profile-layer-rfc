FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

SRC_URI_append = " \
    file://0001-fixup-build-with-gcc6.patch \
"

do_deploy_prepend() {
    ln -sf ${B}/${UBOOT_SREC} ${S}/${UBOOT_SREC}
}
