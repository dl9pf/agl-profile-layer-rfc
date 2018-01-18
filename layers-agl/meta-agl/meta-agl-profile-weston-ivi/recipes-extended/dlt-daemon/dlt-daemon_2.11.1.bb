SUMMARY = "Diagnostic Log and Trace"
DESCRIPTION = "This component provides a standardised log and trace interface, \
based on the standardised protocol specified in the AUTOSAR standard 4.0 DLT. \
This component can be used by GENIVI components and other applications as \
logging facility providing: \
- the DLT shared library \
- the DLT daemon, including startup scripts \
- the DLT daemon adaptors- the DLT client console utilities \
- the DLT test applications"
HOMEPAGE = "https://www.genivi.org/"
SECTION = "console/utils"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=99ba60c3fad7eaf8c56bca6dd75cba09 \
                    file://MPL.txt;md5=ccdb2761cef70c8b2612624c323f89dc"

DEPENDS = "dbus zlib pigz-native"
do_unpack[depends] += "pigz-native:do_populate_sysroot"

# FIX ME
# This should be TAG = "v${PV}" but yocto doesn't support lightweight tags for now
# https://bugzilla.yoctoproject.org/show_bug.cgi?id=6881
TAG = "b8b3ea3a31f7deb681941a19878c82e7b7350ae7"
BRANCH = "v2.11.x"
SRC_URI = "git://git.projects.genivi.org/${PN}.git;branch=${BRANCH};tag=${TAG} \
           file://0001-Fix-build-with-systemd-209.patch \
           file://0002-Don-t-execute-processes-as-a-specific-user.patch \
           file://0003-systemd-unit-type-should-be-in-lowercase-so-use-Type.patch \
           file://0004-Modify-systemd-config-directory.patch"
S = "${WORKDIR}/git"

inherit autotools gettext cmake systemd

# -fPIC is needed to prevent relocation errors when we compile gtest with
# Yocto security flags. See this issue for more details:
#
# https://github.com/google/googletest/issues/854
#
# If that issue is fixed, we can probably remove the manual -fPIC flags here.
OECMAKE_C_FLAGS += "-fPIC"
OECMAKE_CXX_FLAGS += "-fPIC"

PACKAGES += "${PN}-systemd"
SYSTEMD_PACKAGES = "${PN} ${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "dlt-system.service dlt.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"
SYSTEMD_SERVICE_${PN}-systemd = "dlt-example-user.service \
    dlt-dbus.service \
    dlt-adaptor-udp.service \
    dlt-receive.service"
SYSTEMD_AUTO_ENABLE_${PN}-systemd = "disable"

EXTRA_OECMAKE = "-DWITH_SYSTEMD=ON"

FILES_${PN}-doc += "/usr/share/dlt-filetransfer"
