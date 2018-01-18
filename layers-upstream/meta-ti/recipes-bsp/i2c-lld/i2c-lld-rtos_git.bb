SUMMARY = "TI RTOS low level driver for Inter-IC module (I2C)"

inherit ti-pdk

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://I2C.h;beginline=1;endline=32;md5=147a6773e962b103cd162ca58535289c"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|keystone|omapl1|c66x"
PACKAGE_ARCH = "${MACHINE_ARCH}"

I2C_LLD_GIT_URI = "git://git.ti.com/keystone-rtos/i2c-lld.git"
I2C_LLD_GIT_PROTOCOL = "git"
I2C_LLD_GIT_BRANCH = "master"
I2C_LLD_GIT_DESTSUFFIX = "git/ti/drv/i2c"

# Below commit ID corresponds to "DEV.I2C_LLD.01.00.00.07A"
I2C_LLD_SRCREV = "85ad56fb6e1f2bdabdd3c371b97170cb62a6fdd7"

BRANCH = "${I2C_LLD_GIT_BRANCH}"
SRC_URI = "${I2C_LLD_GIT_URI};destsuffix=${I2C_LLD_GIT_DESTSUFFIX};protocol=${I2C_LLD_GIT_PROTOCOL};branch=${BRANCH}"

SRCREV = "${I2C_LLD_SRCREV}"
PV = "01.00.00.07A"
PR = "r0"

DEPENDS_append = " osal-rtos \
"
DEPENDS_append_ti33x = " starterware-rtos \
"
DEPENDS_append_ti43x = " starterware-rtos \
"

# Build with make instead of XDC
TI_PDK_XDCMAKE = "0"

S = "${WORKDIR}/${I2C_LLD_GIT_DESTSUFFIX}"

export PDK_I2C_ROOT_PATH ="${WORKDIR}/build"
export DEST_ROOT="${S}"

# HTML doc link params
PDK_COMP_LINK_TEXT = "I2C LLD"
