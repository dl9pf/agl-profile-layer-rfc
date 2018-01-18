FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI_append = "\
    file://0001-Allow-regular-users-to-launch-Weston_2.0.0.patch \
    file://0001-ivi-shell-rework-goto-labels-to-avoid-memory-leaks.patch \
    file://0002-ivi-shell-removed-assert.patch \
    file://0003-ivi-shell-introduction-of-IVI_INVALID_ID.patch \
    file://0004-layout-interface-added-interface-to-change-surface-id.patch \
    file://0005-ivi-layout-introcuded-configure_desktop_changed.patch \
    file://0006-ivi-layout-introcuded-surface_create_and_configure.patch \
    file://0007-ivi-shell-linked-libweston-desktop-and-added-structs.patch \
    file://0008-ivi-layout-use-libweston-desktop-api-for-views.patch \
    file://0009-ivi-shell-added-libweston-desktop-api_implementation.patch \
    file://0010-ivi-shell-remove-surface_destroy_listener.patch \
    file://0011-ivi-shell-create-weston-desktop-in_wet_shell_init.patch \
    file://0012-hmi-controller-register-for-desktop_surface_configured.patch \
    file://0013-simple-egl-remove-ivi-application-support.patch \
    file://0014-simple-shm-remove-ivi-application-support.patch \
    file://0015-window-client-remove-ivi-application-support.patch \
    file://0016-ivi-shell_add_screen_remove_layer_api.patch \
    "

EXTRA_OECONF_append = " --enable-sys-uid"
