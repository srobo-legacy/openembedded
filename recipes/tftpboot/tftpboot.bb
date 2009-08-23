DESCRIPTION = "Copy Buildresults to /tftpboot/Angstrom/${MACHINE}"
LICENSE = "GPL"
PR = "r1"

SRC_URI = "file://tftpboot.tar.bz2"

DEST_DIR=/tftpboot/Angstrom

DEPENDS = x11-gpe-image
do_install() {
	mkdir -p ${DEST_DIR}
	cp -r ${DEPLOY_DIR_IMAGE}	${DEST_DIR}
}

