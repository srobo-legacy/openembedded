DESCRIPTION = "at91bootstrap: loaded into internal SRAM by AT91 BootROM"
SECTION = "bootloaders"

PR = "r0"

SRC_URI = "ftp://www.at91.com/pub/buildroot/${PN}-${PV}.tar.bz2 \
	"

include	at91bootstrap-${PN}/SRC_URI_append.inc

# This is the way to add a custom defconfig
SRC_URI_append_at91customek = \
	file://defconfig \
	#

SRC_URI_append_at91sam9g45ek = "\
	file://at91bootstrap-2.13-rc4-002-debug.patch;patch=1 \
	"

require at91bootstrap.inc
