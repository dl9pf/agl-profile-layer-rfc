SUMMARY = "TI Fault management module for keystone devices"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://fault_mgmt.h;beginline=1;endline=41;md5=4be5df2b9c314da729e9e4f6cc0b2979"

COMPATIBLE_MACHINE = "k2hk-evm|k2e-evm|k2l-evm"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FAULT_MANAGEMENT_GIT_URI = "git://git.ti.com/keystone-rtos/fault_mgmt.git"
FAULT_MANAGEMENT_GIT_PROTOCOL = "git"
FAULT_MANAGEMENT_GIT_BRANCH = "master"
FAULT_MANAGEMENT_GIT_DESTSUFFIX = "git/ti/instrumentation/fault_mgmt"

# Below commit ID corresponds to "DEV.FAULT_MGMT.01.00.01.03"
FAULT_MANAGEMENT_SRCREV = "a300e7d1f262bdfa48c92851bad0da228a41acc3"

BRANCH = "${FAULT_MANAGEMENT_GIT_BRANCH}"
SRC_URI = "${FAULT_MANAGEMENT_GIT_URI};destsuffix=${FAULT_MANAGEMENT_GIT_DESTSUFFIX};protocol=${FAULT_MANAGEMENT_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${FAULT_MANAGEMENT_SRCREV}"
PV = "01.00.01.03"
PR = "r0"

S = "${WORKDIR}/${FAULT_MANAGEMENT_GIT_DESTSUFFIX}"

DEPENDS_append_keystone = " qmss-lld-rtos \
                            cppi-lld-rtos \
                            pa-lld-rtos \
"

DEPENDS_append_k2hk-evm = " aif2-lld-rtos \
"
# HTML doc link params
PDK_COMP_LINK_TEXT = "Fault Management"
