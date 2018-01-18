##################################################################################
# This patch is temporary.
# This is a solution to the Bug-AGL SPEC-539.
#
# It renames the file udev-smack-default.rules
# as the file 55-smack-default.rules before installation in do_install.
# This comes from https://github.com/01org/meta-intel-iot-security.git
# (meta-security-smack/recipes-core/systemd/)
# It should be removed when the security layer will be refited to meta-security
# See git clone http://git.yoctoproject.org/cgit/cgit.cgi/meta-security
#
# It also renames the file touchscreen.rules as the file 55-touchscreen.rules
# This comes with the recipe systemd_230 of poky (meta/recipes-core/systemd)
# It should be removed when poky changes.
##################################################################################
do_install_prepend() {
	mv ${WORKDIR}/udev-smack-default.rules ${WORKDIR}/55-smack-default.rules || true
	mv ${WORKDIR}/touchscreen.rules ${WORKDIR}/55-touchscreen.rules || true
}

