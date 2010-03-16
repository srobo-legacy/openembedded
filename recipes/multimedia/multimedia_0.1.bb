DESCRIPTION = "MultiMedia files"
PR = "r1"

# This package allows you to add multimedia contents to the file system
# To add this, you create the file "multimedia.tar.bz2." and replace
# the default file included in the openembedded repository.
# Note '.' at end of the filename to avoid decompress by bitbake

SRC_URI = "\
	file://multimedia.tar.bz2. \
	"

do_install() {
	install -d ${D}/usr/share/multimedia
	tar jxvf ${WORKDIR}/multimedia.tar.bz2.	-C ${D}/usr/share/multimedia
	rm -f ${D}/usr/share/multimedia/.empty
}

FILES_${PN} = "\
	/usr/share/multimedia/* \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"
