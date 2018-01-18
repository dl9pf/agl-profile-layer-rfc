SUMMARY = "Provides a set of tools for development for AGL DISTRO"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
        strace \
        ldd \
        less \
        vim \
        lsof \
        gdb \
        valgrind \
        perf \
        htop \
        powertop \
        latencytop \
        systemtap \
        screen \
        usbutils \
        rsync \
        tree \
        pstree \
        "
