DESCRIPTION = "OP-TEE Linux Driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"
inherit module

PN = "optee-linuxdriver"
PR = "r0"
BRANCH = "rcar_gen3"
SRC_URI = " \
    git://github.com/renesas-rcar/optee_linuxdriver.git;branch=${BRANCH} \
"
SRCREV = "3fd77f0dd8a070945f869ec706ebe48fc0d27c20"

PV = "1.1.0+renesas+git${SRCPV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

LDFLAGS[unexport] = "1"

do_configure[noexec] = "1"
# We need to be staged before do_complie. This recipe does not execute do_configure.
do_complile[depends] += "virtual/kernel:do_shared_workdir"

do_compile() {
    # Build kernel module
    cd ${S}/
    make -C ${KBUILD_OUTPUT} M=${S} modules
}

do_install() {
    # Create shared directories
    install -d ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -d ${STAGING_KERNEL_DIR}/include
    install -d ${STAGING_KERNEL_DIR}/include/arm_common
    install -d ${STAGING_KERNEL_DIR}/include/linux

    # Install kernel module
    install -m 0644 ${S}/core/optee.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${S}/armtz/optee_armtz.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/


    # Install shared library to STAGING_KERNEL_DIR for reference from other modules
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/Module.symvers ${STAGING_KERNEL_DIR}/include/optee.symvers

    # Install shared header files to STAGING_KERNEL_DIR
    # This file installed in SDK by kernel-devsrc pkg.
    install -m 0644 ${S}/include/arm_common/optee_msg.h ${STAGING_KERNEL_DIR}/include/arm_common/
    install -m 0644 ${S}/include/arm_common/optee_smc.h ${STAGING_KERNEL_DIR}/include/arm_common/
    install -m 0644 ${S}/include/linux/tee_client_api.h ${STAGING_KERNEL_DIR}/include/linux/
    install -m 0644 ${S}/include/linux/tee_core.h ${STAGING_KERNEL_DIR}/include/linux/
    install -m 0644 ${S}/include/linux/tee_ioc.h ${STAGING_KERNEL_DIR}/include/linux/
    install -m 0644 ${S}/include/linux/tee_kernel_api.h ${STAGING_KERNEL_DIR}/include/linux/
}

PACKAGES = "\
    ${PN} \
    ${PN}-armtz \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/extra/optee.ko \
"

FILES_${PN}-armtz = " \
    /lib/modules/${KERNEL_VERSION}/extra/optee_armtz.ko \
"

RPROVIDES_${PN} += " \
    optee-linuxdriver \
    kernel-module-optee \
    kernel-module-optee-armtz \
    kernel-module-optee-${KERNEL_VERSION} \
    kernel-module-optee-armtz-${KERNEL_VERSION} \
"
