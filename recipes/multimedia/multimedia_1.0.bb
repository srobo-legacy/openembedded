DESCRIPTION = "MultiMedia files"
PR = "r0"

# This package allows you to add multimedia contents to the file system
# To add this, you define 
# MULTIMEDIA_FILES=<filename>.tar.bz2 in local.conf
# and include "multimedia" in your image

do_install() {
	if [ "x${MULTIMEDIA_FILES}" != "x" ] ; then
		install -d ${D}/usr/share/multimedia
		tar jxvf ${MULTIMEDIA_FILES}	-C ${D}/usr/share/multimedia
	fi
}

FILES_${PN} = "\
	/usr/share/multimedia/* \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"
