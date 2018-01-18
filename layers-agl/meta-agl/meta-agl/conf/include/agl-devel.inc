IMAGE_FEATURES =+ "debug-tweaks eclipse-debug tools-debug tools-profile"

IMAGE_INSTALL_append = " \
    packagegroup-agl-devel \
"

IMAGE_INSTALL_append = "${@bb.utils.contains_any('IMAGE_FSTYPES', [ 'vmdk', 'vmdk.xz' ], ' open-vm-tools vboxguestdrivers', '', d)}"

# disable install of debug files in SDK
# initial value: SDKIMAGE_FEATURES="dev-pkgs dbg-pkgs staticdev-pkgs"
SDKIMAGE_FEATURES="dev-pkgs"

# allows insertion of code or items specific to developement
OVERRIDES .= ":agl-devel"
DISTRO_FEATURES_append = " agl-devel"
