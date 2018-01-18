FILESEXTRAPATHS_prepend := "${THISDIR}/pulseaudio-10.0:"

SRC_URI += " \
	file://0001-install-files-for-a-module-development.patch \
	file://0002-volume-ramp-additions-to-the-low-level-infra.patch \
	file://0003-volume-ramp-adding-volume-ramping-to-sink-input.patch \
	file://0004-sink-input-Code-cleanup-regarding-volume-ramping.patch \
	file://0005-sink-input-volume-Add-support-for-volume-ramp-factor.patch \
	file://0006-sink-input-Remove-pa_sink_input_set_volume_ramp.patch;apply=no \
	file://enable-ofono-hfp-backend.patch \
"

PACKAGES =+ " pulseaudio-module-dev"

FILES_pulseaudio-module-dev = "${includedir}/pulsemodule/* ${libdir}/pkgconfig/pulseaudio-module-devel.pc"
