# Enable network bootable image and initrd/initramfs

# add image classes for uboot
IMAGE_CLASSES += "${@'image_types_uboot' if (d.getVar("KERNEL_IMAGETYPE", True) == "uImage") else ''}"

python () {
	if (bb.utils.contains_any("IMAGE_FSTYPES",["live","vmdk","vmdk.xz"],True,False,d)):
		# typical case for Minnowboard Max
		d.setVar("INITRD_IMAGE","initramfs-netboot-image")
		d.setVar("INITRD_IMAGE_LIVE",d.getVar("INITRD_IMAGE",True))
		d.setVar("INITRD_LIVE","%s/%s-%s.ext4.gz" % (
			d.getVar("DEPLOY_DIR_IMAGE",True),
			d.getVar("INITRD_IMAGE_LIVE",True),
			d.getVar("MACHINE",True)
		))
	else:
		d.setVar("INITRAMFS_IMAGE","initramfs-netboot-image")
		if (d.getVar("KERNEL_IMAGETYPE",True) == "uImage"):
			# case for "old" u-boot images, like Porter board
			d.setVar("NETBOOT_FSTYPES", "ext4.gz.u-boot");
		else:
			# case for new u-boot images which don't require uImage format 
			d.setVar("NETBOOT_FSTYPES", "ext4.gz");
}

