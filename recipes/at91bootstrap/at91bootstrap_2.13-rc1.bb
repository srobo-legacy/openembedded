require at91bootstrap.inc
PR = "r1"


SRC_URI = "ftp://www.at91.com/pub/buildroot/${PN}-${PV}.tar.bz2 \
	   file://NAND-empty-1MB.jffs2.bz2 \
	   file://SD-card-tools.tar.bz2 \
	"

SRC_URI_append_at91sam9g45ek = " \
	file://at91sam9g45ekes/AT91SAM9G45_RomCode_Replacement.zip \
	"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

FILES_${PN} = "\
	/home/root/* \
	"

