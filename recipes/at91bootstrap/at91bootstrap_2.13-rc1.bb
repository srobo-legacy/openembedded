require at91bootstrap.inc
PR = "r1"


SRC_URI = "ftp://www.at91.com/pub/buildroot/${PN}-${PV}.tar.bz2 \
           "

SRC_URI_append_at91sam9g45ek = " \
	file://AT91SAM9G45_RomCode_Replacement.zip \
	file://README.txt \
	"

do_configure_prepend() {
	install -d ${DEPLOY_DIR_IMAGE}

	if test -e ${WORKDIR}/AT91SAM9G45_RomCode_Replacement.zip ; then
		cp ${WORKDIR}/AT91SAM9G45_RomCode_Replacement.zip	${DEPLOY_DIR_IMAGE}
	fi
	if test -e ${WORKDIR}/README.txt ; then
		cp ${WORKDIR}/README.txt				${DEPLOY_DIR_IMAGE}
	fi
}

PARALLEL_MAKE = ""

