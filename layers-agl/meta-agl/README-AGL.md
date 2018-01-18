?README-AGL.md: The Automotive Grade Linux Distribution
======================================================

AGL is creating an automotive specific Linux distribution (AGL UCB)
that unifies the software that has been written in a number of places already,
such as GENIVI and Tizen IVI.

The layer 'meta-agl' provides a minimal set of software
to boot system of AGL Distribution. 'meta-agl' is the minimal
core which is used build AGL profiles on top of it.

The reference UI is part of 'meta-agl-demo'.

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

The latest version is available at [AGL wiki](https://wiki.automotivelinux.org/agl-distro/source-code) for the following targets:
* [QEMU](https://wiki.automotivelinux.org/agl-distro/source-code)
* [Renesas Porter board](https://wiki.automotivelinux.org/start/building_for_the_renesas_r-car_m2)
* [Raspberry Pi 2/3](https://wiki.automotivelinux.org/agl-distro/agl-raspberrypi)

To build an image from 'meta-agl'

1. Prepare repo:
     >      $ mkdir ~/bin
     >      $ export PATH=~/bin:$PATH
     >      $ curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
     >      $ chmod a+x ~/bin/repo

2. Get all layers:
     >      $ repo init -u https://gerrit.automotivelinux.org/gerrit/AGL/AGL-repo
     >      $ repo sync

3. Check available targets and extensions, select target machine:
     >      $ source meta-agl/scripts/aglsetup.sh -h
     >      $ source meta-agl/scripts/aglsetup.sh -m porter

4. Build agl-image-ivi
     >      $ bitbake agl-image-ivi

To build the agl-demo-platform (as demo'ed @ALS) replace 3. and 4. with:
3. Check available targets and extensions, select target machine:
     >      $ source meta-agl/scripts/aglsetup.sh -h
     >      $ source meta-agl/scripts/aglsetup.sh -m porter agl-demo [agl-appfw-smack] [agl-devel] [agl-netboot]

4. Build agl-demo-platform
     >      $ bitbake agl-demo-platform
Note: this uses more layers and not only meta-agl

AGL Layers and dependencies:
----------------------------
Check the README.md of the respective layers:
- meta-agl              (= core, no UI)
- meta-agl-demo    (= reference UI)
- meta-agl-extra     (= extra components)

In addition, the reference hardware (Renesas R-Car Gen2 / porter) has this dependency:

URI: https://gerrit.automotivelinux.org/gerrit/AGL/meta-renesas
> branch:   agl-1.0-bsp-1.8.0
> tested revision: 82611ccadef36ab0b8a6fd6fb1cf055e115f1ef5


Supported Machines
------------------

Reference hardware:

* QEMU (x86-64) - emulated machine: qemux86-64
* Renesas R-Car Gen2 (R-Car M2) - machine: porter

Community contributed hardware:

* See: https://wiki.automotivelinux.org/agl-distro#supported_hardware


Supported Target of bitbake
---------------------------

meta-agl:

* `agl-image-ivi` The baseline image of AGL Distributions (console only)

* `agl-image-minimal` For internal use to develop distribution (experimental)
* `agl-image-weston`  For internal use to develop distribution (experimental)

meta-agl-demo:

* `agl-demo-platform` The demo/reference image (with graphical UI)

Run this command for a full list of machines, features and targets:
     >       $ source ./meta-agl/scripts/aglsetup.sh -h


Supposed Directory Tree of Layers to build
-------------------------------------------

     >      $ repo init -u https://gerrit.automotivelinux.org/gerrit/AGL/AGL-repo
     >      $ repo sync
     >      $ tree -L 1
	.
	|-- meta-agl
	|-- meta-agl-demo
	|-- meta-agl-devel
	|-- meta-agl-extra
	|-- meta-amb
	|-- meta-fsl-arm
	|-- meta-fsl-arm-extra
	|-- meta-intel
	|-- meta-intel-iot-security
	|-- meta-openembedded
	|-- meta-qcom
	|-- meta-qt5
	|-- meta-raspberrypi
	|-- meta-renesas
	|-- meta-rust
	|-- meta-security-isafw
	|-- meta-ti
	`-- poky
	18 directories, 0 files


Build a QEMU image
------------------

You can build a QEMU image using the following steps:

(latest version here: https://wiki.automotivelinux.org/agl-distro/source-code)
To build an image from 'meta-agl'

1. Prepare repo:
     >      $ mkdir ~/bin
     >      $ export PATH=~/bin:$PATH
     >      $ curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
     >      $ chmod a+x ~/bin/repo

2. Get all layers:
     >      $ repo init -u https://gerrit.automotivelinux.org/gerrit/AGL/AGL-repo
     >      $ repo sync

3. Check available targets and extensions, select target machine:
     >      $ source meta-agl/scripts/aglsetup.sh -h
     >      $ source meta-agl/scripts/aglsetup.sh -m qemux86-64

  Alternatively, to enable the reference UI feature ('agl-demo'):
     >      $ source meta-agl/scripts/aglsetup.sh -m qemux86-64 agl-demo

  Alternatively, to also enable also the security framework ('agl-appfw-smack'):
     >      $ source meta-agl/scripts/aglsetup.sh -m qemux86-64 agl-demo agl agl-appfw-smack

  Alternatively, to also enable debugging tools ('agl-devel') and the network-boot (nbd) capabilities ('agl-netboot'):
     >      $ source meta-agl/scripts/aglsetup.sh -m qemux86-64 agl-demo agl agl-appfw-smack agl-devel agl-netboot

4. Build agl-image-ivi
     >      $ bitbake agl-image-ivi

  or alternatively, if you enabled the 'agl-demo' feature:
     >      $ bitbake agl-demo-platform

 - If you want to run QEMU directly as VM in Virtual Box or your other favorite VM software then add this line to your "conf/local.conf" file.
     >      IMAGE_FSTYPES += "vmdk"

5. Run in the emulator
     >      $ runqemu agl-image-ivi qemux86-64
     >      or
     >      $ runqemu agl-demo-platform qemux86-64

   For a large screen:
     >      $ runqemu agl-image-ivi qemux86-64 bootparams="uvesafb.mode_option=1280x720-32"

   To extend the amount of memory, add to runqemu:
     qemuparams="-m 512"
     >      $ runqemu agl-image-ivi qemux86-64 qemuparame="-m 512" bootparams="uvesafb.mode_option=1280x720-32"


   To separate console from VGA screen (to avoid corrupt screen while booting),
   add to runqemu:
     serial
     >      $ runqemu agl-image-ivi qemux86-64 bootparams="uvesafb.mode_option=1280x720-32" serial

	**Or use the virtual disk in Virtual Box from this location:**
	> tmp/deploy/images/qemux86-64/agl-demo-platform-qemux86-64.vmdk

6.  Some weston samples are available from weston terminal (click top left icon).
   Check the folder `/opt/AGL/ALS2016`.

Build a R-Car M2 (porter) image
-------------------------------

### Software setup

NOTE: You will need to download the matching binary driver package from renesas.
      As of this writing, the version from 20151228.
      The main URL to retrieve these is:

 -   https://www.renesas.com/en-eu/solutions/automotive/rcar-demoboard.html

      As of this writing, the necessary libary and driver packages linked to
      by above site are:
      - The subpage for the library is:
	      - https://www.renesas.com/en-eu/software/D3017410.html (registration/login required)

      - The sub-page for the driver is:
	      - https://www.renesas.com/en-eu/media/secret/solutions/automotive/rcar-demoboard/R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers-20151228.zip

      !!! The files need to be in `${HOME}/Downloads` ( `$XDG_DOWNLOAD_DIR` ) !!!

#### Getting Source Code and Build image

(latest version here: https://wiki.automotivelinux.org/agl-distro/source-code)
To build an image from 'meta-agl' only:

1. Prepare repo:
     >      $ mkdir ~/bin
     >      $ export PATH=~/bin:$PATH
     >      $ curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
     >      $ chmod a+x ~/bin/repo

2. Get all layers:
     >      $ repo init -u https://gerrit.automotivelinux.org/gerrit/AGL/AGL-repo
     >      $ repo sync

3. Check available targets and extensions, select target machine:
     >      $ source meta-agl/scripts/aglsetup.sh -h
     >      $ source meta-agl/scripts/aglsetup.sh -m porter

  Alternatively, to enable the reference UI:
     >      $ source meta-agl/scripts/aglsetup.sh -m porter agl-demo

  Alternatively, to also enable also the security framework:
     >      $ source meta-agl/scripts/aglsetup.sh -m porter agl-demo agl agl-appfw-smack

  Alternatively, to also enable debugging tools and the network-boot (nbd) capabilities:
     >      $ source meta-agl/scripts/aglsetup.sh -m porter agl-demo agl agl-appfw-smack agl-devel agl-netboot

4. (optional) edit conf/local.conf   )* see below
     >        $ vi conf/local.conf

5. Build agl-image-ivi
     >        $ bitbake agl-image-ivi

  or alternatively, if you enabled the 'agl-demo' feature:
     >      $ bitbake agl-demo-platform


)* :

 4 .  Optional edits to conf/local.conf

 *  If you want to use multimedia accelerations, uncomment
    manually 4 `IMAGE_INSTALL_append_porter` in conf/local.conf.

    >     #IMAGE_INSTALL_append_porter = " \
    >     #    gstreamer1.0-plugins-bad-waylandsink \
    >     #    "
    >     #IMAGE_INSTALL_append_porter = " \
    >     #    gstreamer1.0-plugins-base-videorate \
    >     ...
    >     #"
    >     #IMAGE_INSTALL_append_porter = " \
    >     #    libegl libegl-dev libgbm-dev \
    >     ...
    >     #    "
    >     #IMAGE_INSTALL_append_porter = " \
    >     #    packagegroup-rcar-gen2-multimedia \
    >     ...
    >     #    "

* Also it is needed to uncomment this:
    >     #MACHINE_FEATURES_append = " multimedia"

   This `multimedia` MACHINE_FEATURES enables the meta-renesas's specific multimedia configuration.
   The version of GStreamer1.0 which AGL distro will use is changed
   to 1.2.3 (meta-renesas prefers it) over 1.4.1 (meta-agl default)
   by this switch.

* If you want to install various Qt5 examples, add below
   configuration to your local.conf.
>      IMAGE_INSTALL_append = " \
>            packagegroup-agl-demo-qt-examples \
>        "
>        PACKAGECONFIG_append_pn-qtbase = " examples"
>
>  IMPORTANT NOTE:
>        To run examples with wayland-egl plugin,
>        use ``LD_PRELOAD=/usr/lib/libEGL.so <command>``.
>        If not, programs should not launch by error,
>        'EGL not available'.


### Deployment (SDCARD)

#### Instructions on the host

1. Format SD-Card and then, create single EXT4 partition on it.

2. Mount the SD-Card, for example `/media/$SDCARD_LABEL`.

3. Copy AGL root file system onto the SD-Card
   1. Go to build directory
         >     $ cd $AGL_TOP/build/tmp/deploy/images/porter

   2. Extract the root file system into the SD-Card
         >     $ sudo tar --extract --numeric-owner --preserve-permissions --preserve-order \
         >                --totals --directory=/media/$SDCARD_LABEL --file=agl-image-ivi-porter.tar.bz2

   3. Copy kernel and DTB into the `/boot` of the SD-Card
         >     $ sudo cp uImage uImage-r8a7791-porter.dtb /media/$SDCARD_LABEL/boot

4. After the copy finished, unmount SD-Card and insert it into the SD-Card slot of the porter board.

#### Instructions on the target

NOTE: There is details about porter board [here](http://elinux.org/R-Car/Boards/Porter).

NOTE: To boot weston on porter board, we need keyboard and mouse. (USB2.0 can be use for this)

##### Change U-Boot parameters to boot from SD card

1. Power up the board and, using your preferred terminal emulator, stop the board's autoboot by hitting any key.

  ``Debug serial settings are 38400 8N1. Any standard terminal emulator program can be used.**

2. Set the follow environment variables and save them
>     => setenv bootargs_console console=ttySC6,${baudrate}
>     => setenv bootargs_video vmalloc=384M video=HDMI-A-1:1024x768-32@60
>     => setenv bootcmd_sd 'ext4load mmc 0:1 0x40007fc0 boot/uImage;ext4load mmc 0:1 0x40f00000 boot/uImage-r8a7791-porter.dtb'
>     => setenv bootcmd 'setenv bootargs ${bootargs_console} ${bootargs_video} root=/dev/mmcblk0p1 rw rootfstype=ext4;run bootcmd_sd;bootm 0x40007fc0 - 0x40f00000'
>     => saveenv

##### Boot from SD card

1. After board reset, U-Boot is started and after a countdown, ...
   Linux boot message should be displayed. Please wait a moment.

2. Then weston is booted automatically, and weston-terminal appears.

3. Have fun! :)

### Deployment (TFTP/NFS)

NOTE: These instructions are based on Embedded Linux Wiki, [here](http://www.elinux.org/R-Car/Boards/Yocto#Loading_kernel_via_TFTP_and_rootfs_via_NFS). And a Debian (wheezy, ip: 192.168.30.70) is used as the host for this instructions.

#### Instructions on the host
1. Setup a TFTP server
   1. Install necessary packages
         >     $ sudo apt-get install tftp tftpd-hpa

   2. Go to build directory, and copy kernel and DTB into TFTP server root (default server dir: /srv/tftp)
         >     $cd  $AGL_TOP/build/tmp/deploy/images/porter
         >     $ sudo cp uImage uImage-r8a7791-porter.dtb /srv/tftp

   3. Verify TFTP server is working
         >     $ ls uImage
           ls: cannot access uImage: No such file or directory

         >     $ cd /tmp/
         >     $ tftp 192.168.30.70
         >     tftp> get uImage
         >     Received 3583604 bytes in 0.2 seconds
         >     tftp> q
         >     $ ls uImage
         >     uImage

2. set NFS server
   1. Install necessary packages
         >     $ sudo apt-get install nfs-kernel-server nfs-common

   2. Go to build directory, and extract the root file system into a dedicated directory (here we use /nfs/porter)
         >     $ cd $AGL_TOP/build/tmp/deploy/images/porter
         >     $ sudo mkdir -p /nfs/porter
         >      $ sudo tar --extract --numeric-owner --preserve-permissions --preserve-order \
         >       --totals --directory=/nfs/porter --file=agl-demo-platform-porter.tar.bz2

   3. Edit /etc/exports
         >     $ sudo vi /etc/exports
         Add
         >
         >     /nfs/porter	*(rw,no_subtree_check,sync,no_root_squash,no_all_squash)
         >
	Save the file and exit.

   4. Restart nfs service
         >     $ sudo service nfs-kernel-server restart

   5. Verify NFS server is working
         >     $ sudo mount -t nfs 192.168.30.70:/nfs/porter /tmp/
         >     $ ls /tmp
         >     bin  boot  dev  etc  home  lib  media  mnt  proc  run  sbin  sys  tmp  usr  var

#### Instructions on the target board

NOTE: There is details about porter board [here](http://elinux.org/R-Car/Boards/Porter).

NOTE: To boot weston on porter board, we need keyboard and mouse. (USB2.0 can be use for this)

##### Change U-Boot parameters to boot from TFTP/NFS

1. Power up the board and, using your preferred terminal emulator, stop the board's autoboot by hitting any key.

  > Debug serial settings are 38400 8N1. Any standard terminal emulator program can be used.

2. Set the follow environment variables and save them
>     => setenv ipaddr <board-ip>
>     => setenv serverip <host-ip>
>     => setenv bootargs_console console=ttySC6,${baudrate}
>     => setenv bootargs_video vmalloc=384M video=HDMI-A-1:1024x768-32@60
>     => setenv bootcmd_net 'tftp 0x40007fc0 uImage; tftp 0x40f00000 uImage-r8a7791-porter.dtb'
>     => setenv bootcmd 'setenv bootargs ${bootargs_console} ${bootargs_video} ip=${ipaddr} root=/dev/nfs nfsroot=${serverip}:/nfs/porter,vers=3;run bootcmd_net;bootm 0x40007fc0 - 0x40f00000'
>     => saveenv

    Replace <board-ip> with a proper IP address for the board, like 192.168.30.60.
    Replace <host-ip> with the IP address of the host, here we use 192.168.30.70.

##### Boot from TFTP/NFS

1. After board reset, U-Boot is started and after a countdown, ...
   Linux boot message should be displayed. Please wait a moment.
2. Then weston is booted automatically, and weston-terminal appears.
3. Have fun! :)

