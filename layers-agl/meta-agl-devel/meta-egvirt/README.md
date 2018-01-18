Virtualization support (meta-egvirt)
======================================================

meta-egvirt is the AGL Virtualization Expert Group (EG-VIRT) layer. It aims to
design, test, implement and assess virtualization technologies (containers,
hypervisors, system partitioners and other solutions) for AGL ARMv8 and Intel
platforms.

The primary hardware target of such work is for the time being the Renesas
R-Car M3 development platform (ARMv8). Other solutions will be added in future,
depending on their availability and on the community interest.

Maintainers:
	Michele Paolino <m.paolino@virtualopensystems.com>


How to enable agl virtualization feature
------------------------------------------------------
In order to enable the agl virtualization features (agl-egvirt),
each supported board needs to add in one of its configuration
files (see for instance meta-agl/meta-agl-bsp/conf/include)
the following line:

MACHINE_FEATURES += "agl-egvirt"

References
------------------------------------------------------
EG-VIRT wikipage:
	https://wiki.automotivelinux.org/eg-virt
EG-VIRT meetings:
	https://wiki.automotivelinux.org/eg-virt-meetings
