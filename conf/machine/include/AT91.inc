# Common description for Atmel ARM processors

TARGET_ARCH = "arm"
#PACKAGE_EXTRA_ARCHS = "armv4t armv5te"

PREFERRED_PROVIDER_virtual/kernel = "linux"
PREFERRED_VERSION_linux = "2.6.30.2"
PREFERRED_PROVIDER_xserver = "xserver-kdrive"
PREFERRED_PROVIDER_at91bootstrap = "at91bootstrap_2.13-rc1"
PREFERRED_PROVIDER_u-boot = "u-boot_2009.08-rc2"

KERNEL_IMAGETYPE = "uImage"

PREFERRED_VERSION_at91bootstrap = "2.13-rc1"
PREFERRED_VERSION_u-boot = "2009.08-rc2"
# PREFERRED_VERSION_mplayer = "svn"

#don't try to access tty1
USE_VT = "0"

MACHINE_FEATURES = "kernel26 alsa ext2 usbgadget vfat uboot"
MACHINE_EXTRA_RDEPENDS += "mpg321 mpd madplay mplayer libmad"
MACHINE_EXTRA_RDEPENDS += "kernel-modules"
MACHINE_EXTRA_RDEPENDS += "minicom"
MACHINE_EXTRA_RDEPENDS += "samba vsftpd"
MACHINE_EXTRA_RDEPENDS += "nano"
# MACHINE_EXTRA_RDEPENDS += "u-boot-utils"


EXTRA_IMAGEDEPENDS += "u-boot"
EXTRA_IMAGEDEPENDS += "at91bootstrap"


# used by sysvinit_2
SERIAL_CONSOLE = "115200 ttyS0"
IMAGE_FSTYPES ?= "jffs2"
EXTRA_IMAGECMD_jffs2 = "--pad --little-endian --eraseblock=0x20000 -n"
#
# Uncomment or add kernel modules that should be autoloaded
#module_autoload_g_file_storage = ${@base_contains('MACHINE_FEATURES', 'usbgadget',  'g_file_storage', '',d)}
#module_autoload_g_ether        = ${@base_contains('MACHINE_FEATURES', 'usbgadget',  'g_ether', '',d)}
module_autoload_g_serial       = ${@base_contains('MACHINE_FEATURES', 'usbgadget',  'g_serial', '',d)}

require conf/machine/include/tune-arm926ejs.inc

