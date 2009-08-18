DESCRIPTION = "U-boot bootloader 2009.08-rc2"
LICENSE = "GPL"
SECTION = "bootloader"
PRIORITY = "optional"
PV = "2009.08-rc2"
SRCREV = "${AUTOREV}"

PR = "r4"

require u-boot.inc

UBOOT_IMAGE = "${MACHINE}-u-boot-${PV}.bin"
UBOOT_SYMLINK = "u-boot.bin"

S = "${WORKDIR}/u-boot-${PV}"
#FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/u-boot-${PV}/atmel"
DEFAULT_PREFERENCE = "-1"

DEFAULT_PREFERENCE_at91sam9g10ek = "2"
DEFAULT_PREFERENCE_at91sam9g20ek = "2"
DEFAULT_PREFERENCE_at91sam9g45ek = "2"
DEFAULT_PREFERENCE_at91sam9m10ek = "2"
DEFAULT_PREFERENCE_at91sam9rlek = "2"
DEFAULT_PREFERENCE_at91sam9260ek = "2"
DEFAULT_PREFERENCE_at91sam9261ek = "2"
DEFAULT_PREFERENCE_at91sam9263ek = "2"
DEFAULT_PREFERENCE_at91capadk = "2"
DEFAULT_PREFERENCE_at91capstk = "2"

SRC_URI = "ftp://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2 "

SRC_URI_append = " \
	file://u-boot-2009.08-rc1-001-nosoftfloat.patch;patch=1 \
	file://u-boot-2009.08-rc1-007-tools.patch;patch=1 \
	file://u-boot-2009.08-rc1-008-Makefile.patch;patch=1 \
	file://u-boot-2009.08-rc1-009-factory.patch;patch=1 \
	file://u-boot-2009.08-rc1-010-coloured_led.patch;patch=1 \
	file://u-boot-2009.08-rc1-012-ethinit.patch;patch=1 \
	file://u-boot-2009.08-rc1-013-configs.patch;patch=1 \
	file://u-boot-2009.08-rc1-014-no-weak-inlines.patch;patch=1 \
	file://u-boot-2009.08-rc1-015-large-u-boot.patch;patch=1 \
	file://u-boot-2009.08-rc1-add-raise.patch;patch=1 \
	file://u-boot-2009.08-rc1-eabi.patch;patch=1 \
	"

TARGET_LDFLAGS = ""

inherit base

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"
#DESTDIR=${DEPLOY_DIR_IMAGE} REVISION=${PR}"
#EXTRA_OEMAKE += "BOARDNAME=${MACHINE}"
#EXTRA_OEMAKE += "BUILD_DATE=${MACHINE}"
#EXTRA_OEMAKE += "U_BOOT_VERSION=${PV}"
#EXTRA_OEMAKE += "TFTPBOOT=/tftpboot"





