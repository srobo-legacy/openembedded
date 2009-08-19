# Copyright Ulf Samuelsson <ulf.samuelsson@atmel.com> (c) 2007
# License: MIT (see http://www.opensource.org/licenses/mit-license.php
#               for a copy of the license)
#
# Filename: atmel-fixes.bb
DESCRIPTION = "Updates for an Atmel root file system"
LICENSE = "GPLv2"
PV = "1.0"
PR = "r0"

SRC_URI = "\
	file://user.jffs2 \
	file://update \
	"

#inherit update-rc.d


copy_file () {
	file=$1
	src=$2
	dst=$3
	if test -e ${src}/${file} ; then
		rm -f ${dst}/${file}
		install -m 755 ${src}/${file}	 ${dst}/${file}
		echo "AT91BOOTSTRAP: copied ${file} to ${dst}"
	else
		echo "AT91BOOTSTRAP.do_install: ${src}/${file} did not exist"
	fi
}

do_install() {
	install -d ${DEPLOY_DIR_IMAGE}
	copy_file user.jffs2 ${WORKDIR} ${DEPLOY_DIR_IMAGE}

	copy_file	update					${WORKDIR}	${DEPLOY_DIR_IMAGE}

	install	-d	${D}/home/root
	copy_file	update					${WORKDIR}	${D}/home/root
}


PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

FILES_${PN} = "\
	/home/root/* \
	"


