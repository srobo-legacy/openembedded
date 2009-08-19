DESCRIPTION = "at91bootstrap: loaded into internal SRAM by AT91 BootROM"
SECTION = "bootloaders"

PR = "r2"

SRC_URI = "ftp://www.at91.com/pub/buildroot/${PN}-${PV}.tar.bz2 \
	   file://NAND-empty-1MB.jffs2.bz2 \
	   file://SD-card-tools.tar.bz2 \
	"

SRC_URI_append_at91sam9g45ekes = " \
	file://at91sam9g45ekes/AT91SAM9G45_RomCode_Replacement_13.zip \
	"

# at91sam9g45ek is not yet available, so add sam9g45ekes
SRC_URI_append_at91sam9g45ek = " \
	file://at91sam9g45ekes/AT91SAM9G45_RomCode_Replacement_13.zip \
	"

# This is the way to add a custom defconfig
SRC_URI_append_at91customek = \
	file://defconfig \
	#

require at91bootstrap.inc
