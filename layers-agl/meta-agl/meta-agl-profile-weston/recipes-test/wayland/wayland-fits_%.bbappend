# Disable GTK+ and EFL tests
PACKAGECONFIG = ""

# UInput driver is necessary on QEMU
RDEPENDS_${PN}_append_qemux86-64 = " kernel-module-uinput"
