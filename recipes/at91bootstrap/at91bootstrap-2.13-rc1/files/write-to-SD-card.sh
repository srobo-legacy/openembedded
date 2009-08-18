#!/bin/sh
# This script will copy a rootfs file system to an sdcard
# Before you start this, you better start the partition editor and fix
# or run mk-sd-card.sh
# Start partition editor
# FAT32 	: 50 MB Primary
# ROOTFS	: <rest> Primary

DEVICE=$1
LINUX=$2
START=$3
ROOTFS=$4
ROOTFSTYPE=$5
U_BOOT=$6
AT91BOOTSTRAP=$7

DISK=/tmp/disk
FAT32_PARTITION=${DISK}1
FAT32_DEVICE=${DEVICE}

ROOTFS_PARTITION=${DISK}2
ROOTFS_DEVICE=${DEVICE}2


function	mount_rootfs ()
{
	mkdir -p ${ROOTFS_PARTITION}
	mount -t ${ROOTFSTYPE} ${ROOTFS_DEVICE} ${ROOTFS_PARTITION}
}

function	unmount_rootfs ()
{
	sync
	umount ${ROOTFS_PARTITION}
	rmdir ${ROOTFS_PARTITION}
}

function	mount_fat32 ()
{
	mkdir -p ${FAT32_PARTITION}
	mount -t vfat ${FAT32_DEVICE} ${FAT32_PARTITION}
}

function	unmount_fat32 ()
{
	sync
	umount ${FAT32_PARTITION}
	rmdir ${FAT32_PARTITION}
}


function	write_rootfs ()
{
	tar xpjfv ${ROOTFS} -c ${ROOTFS_PARTITION}
}

function	write_linux ()
{
	cp ${LINUX} ${FAT32_PARTITION}
}

function	write_u_boot ()
{
	cp ${U_BOOT} ${FAT32_PARTITION}
}

function	write_at91bootstrap ()
{
	cp ${AT91BOOTSTRAP} ${FAT32_PARTITION}/boot.bin
}

function	write_autoscript ()
{
	echo "setenv bootdmd 'mmcinit; fatload mmc 0:1 ${START} uImage; bootm ${START}'" > ${FAT32_PARTITION}/autoscript
	echo "setenv bootargs 'console=ttyS0,115200n8 root=/dev/mmcblk0p2 rootdelay=2 rootfstype=rootfs rw'" >> ${FAT32_PARTITION}/autoscript
	echo "save" >> ${FAT32_PARTITION}/autoscript
}

function	do_rootfs ()
{
	mount_rootfs
	write_rootfs
	unmount_rootfs
}

function	do_fat32 ()
{
	mount_fat32
	write_at91bootstrap
	write_uboot
	write_linux
	unmount_fat32
}



