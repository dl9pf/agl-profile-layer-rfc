DESCRIPTION = "The minimal set of packages for Connectivity Subsystem"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-core-connectivity \
    "

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
    dhcp-server \
    ${@bb.utils.contains('VIRTUAL-RUNTIME_net_manager','connman','connman connman-client connman-tests connman-tools connman-ncurses','',d)} \
    "
