OVERRIDES .= "${@bb.utils.contains('MACHINE_FEATURES', 'agl-egvirt', ':aglegvirt', '', d)}"
IMAGE_INSTALL_append_aglegvirt = " qemu "
