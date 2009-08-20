DESCRIPTION = "at91bootstrap: loaded into internal SRAM by AT91 BootROM"
SECTION = "bootloaders"

PR = "r0"

SRC_URI = "ftp://www.at91.com/pub/buildroot/${PN}-${PV}.tar.bz2 \
	file://at91bootstrap-2.13-rc3.patch;patch=1 \
	"

# This is the way to add a custom defconfig
SRC_URI_append_at91customek = \
	file://defconfig \
	#

require at91bootstrap.inc
