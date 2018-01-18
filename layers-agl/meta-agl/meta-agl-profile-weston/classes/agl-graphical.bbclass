WESTONTTY ??= "1"
WESTONUSER ??= "display"
WESTONGROUP ??= "display"
WESTONARGS ?= "--idle-time=0  --tty=${WESTONTTY} ${@bb.utils.contains("DISTRO_FEATURES", "gst-record", "--gst-record", "",d)}"
WESTONLAUNCHARGS ??= "--tty /dev/tty${WESTONTTY} --user ${WESTONUSER}"
DISPLAY_XDG_RUNTIME_DIR ??= "/run/platform/${WESTONUSER}"

