**README.md for the 'meta-agl' layer.**

**See README-AGL.md for general information about Automotive Grade Linux.**


meta-agl, the core layer for Automotive Grade Linux Distribution
================================================================

AGL is creating an automotive specific Linux distribution that unifies
the software that has been written in a number of places already,
such as GENIVI and Tizen IVI.

The layer 'meta-agl' provides a minimal set of software
to boot system of AGL Distribution. 'meta-agl' is the minimal
core which is used build AGL profiles on top of it.

Especially there is no reference UI included which is part of 'meta-agl-demo'.

Additional components like the security framework are part of 'meta-agl-extra'.

The AGL community appreciates feedback, ideas, suggestion, bugs and
documentation just as much as code. Please join the irc conversation
at the #automotive channel on irc.freenode.net and our mailing list.

For infomation for subscribing to the mailing list
    [automotive-discussions](http://lists.linuxfoundation.org/mailman/listinfo/automotive-discussions)
For information about AGL Distribution, see the
    [AGL Distribution](https://wiki.automotivelinux.org/agl-distro)
For information abount Getting started with AGL
    [here](https://wiki.automotivelinux.org/start/getting-started)
For information about contributing to the AGL Distro
    [here](https://wiki.automotivelinux.org/agl-distro/contributing)

Quick start guide
-----------------
See README-AGL.md


'meta-agl' Layer Dependencies
-----------------------------
* poky
> URI: git://git.yoctoproject.org/poky
> branch         : jethro
> tested revision: 40376446904ae3529be41737fed9a0b650ed167d

* meta-openembedded
> URI: git://git.openembedded.org/meta-openembedded
> layer          : meta-openembedded
> branch         : jethro
> tested revision: 8ab04afbffb4bc5184cfe0655049de6f44269990

 Specifically out of meta-openembedded these sub-layers are used:
	* meta-openembedded/meta-oe
	* meta-openembedded/meta-multimedia
	* meta-openembedded/meta-efl
	* meta-openembedded/meta-networking
	* meta-openembedded/meta-python

Layers
------

There are 5 layers in top-level `meta-agl`.

* `meta-agl/meta-ivi-common`
`meta-ivi-common` is a layer which contains common packages to AGL
Distribution and other platforms for In-Vehicle Infotainment system.

* `meta-agl/meta-agl`
`meta-agl` is a layer which contains AGL common and middleware packages.

* `meta-agl/meta-agl-bsp`
`meta-agl-bsp` is a layer which contains required packages to boot AGL
distribution on an emulated machine(QEMU).

* `meta-agl/meta-netboot`
`meta-netboot` contains the netboot initrd support recipes. This is needed
in case of booting over the network as NFS does not support the securitylabels.

Packagegroups
-------------

AGL package group design:

* packagegroup-agl-image-minimal

        packagegroup-agl-core-automotive.bb
        packagegroup-agl-core-connectivity.bb
        packagegroup-agl-core-graphics.bb
        packagegroup-agl-core-kernel.bb
        packagegroup-agl-core-multimedia.bb
        packagegroup-agl-core-navi-lbs.bb
        packagegroup-agl-core-os-commonlibs.bb
        packagegroup-agl-core-security.bb
        packagegroup-agl-core-speech-services.bb

These are for making image ``agl-image-minimal`` which is small image just
capable of allowing a device to boot.

Subsystem should maintain packagegroup-agl-core-[subsystem].bb which should
hold sufficient packages to build ``agl-image-minimal``.

* packagegroup-agl-image-ivi

        packagegroup-agl-ivi-automotive.bb
        packagegroup-agl-ivi-connectivity.bb
        packagegroup-agl-ivi-graphics.bb
        packagegroup-agl-ivi-kernel.bb
        packagegroup-agl-ivi-multimedia.bb
        packagegroup-agl-ivi-navi-lbs.bb
        packagegroup-agl-ivi-os-commonlibs.bb
        packagegroup-agl-ivi-security.bb
        packagegroup-agl-ivi-speech-services.bb

These are for making image ``agl-image-ivi`` which is baseline for the profiles
of AGL distro. 'Baseline' means Service Layer and Operating System Layer defined
in AGL Spec v1.0.

* packagegroup-agl-test.bb

Additional tools used in QA tests (for agl-image*-qa).

* packagegroup-ivi-common*

        packagegroup-ivi-common-core-automotive.bb
        packagegroup-ivi-common-core.bb
        packagegroup-ivi-common-core-connectivity.bb
        packagegroup-ivi-common-core-graphics.bb
        packagegroup-ivi-common-core-kernel.bb
        packagegroup-ivi-common-core-multimedia.bb
        packagegroup-ivi-common-core-navi-lbs.bb
        packagegroup-ivi-common-core-os-commonlibs.bb
        packagegroup-ivi-common-core-security.bb
        packagegroup-ivi-common-core-speech-services.bb
        packagegroup-ivi-common-test.bb

These are for picking up some packages from upstreams like GENIVI/Tizen/Others.
The layer of ``meta-ivi-common`` has no image to build, all packagegroups are
aggregated to ``packagegroup-ivi-common-core' and it is included by images,
``agl-image-ivi.bb`` and ``agl-demo-platform.bb``.


Supported Machines
------------------

See README-AGL.md


Supported Target for bitbake
----------------------------

meta-agl:

* `agl-image-ivi` The baseline image of AGL Distributions (console only)
* `agl-image-minimal` For internal use to develop distribution (experimental)
* `agl-image-weston`  For internal use to develop distribution (experimental)


