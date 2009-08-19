# Common include file for at91bootstrap-2.13-rc1 and later

S = "${WORKDIR}/at91bootstrap-${PV}"

PACKAGE_ARCH = "${AT91BOOTSTRAP_BOARD}"
PACKAGES = "${PN}"

FILES_${PN} = "\
	/home/root/* \
	"

AT91BOOTSTRAP_BOARD_DIR = "${S}/boards/${AT91BOOTSTRAP_BOARD}"
AT91BOOTSTRAP_BOARD_CONFIG="${AT91BOOTSTRAP_BOARD}_defconfig"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} DESTDIR=${DEPLOY_DIR_IMAGE} REVISION=${PR}"
PARALLEL_MAKE = ""

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	echo "FILESPATH = ${FILESPATH}"
	echo "FILESDIR = ${FILESDIR}"

	rm -Rf ${S}/binaries
	rm	-Rf	${S}/binaries

	# Copy the defconfig file to the board directory, (if we provide one)
	# Atmel supplied boards normally has a valid defconfig
	if	test -e ${WORKDIR}/defconfig ; then
		mkdir -p	${AT91BOOTSTRAP_BOARD_DIR}
		cp ${WORKDIR}/defconfig	${AT91BOOTSTRAP_BOARD}
	fi

	oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}" ${AT91BOOTSTRAP_BOARD_CONFIG}
	oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}"
	oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}" boot
}

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

	oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}" install

	if [ "${AT91BOOTSTRAP_BOARD}x" == "at91sam9g45ekes" ]; then
		copy_file	AT91SAM9G45_RomCode_Replacement.bin	${WORKDIR}	${DEPLOY_DIR_IMAGE}
		copy_file	README.txt				${WORKDIR}	${DEPLOY_DIR_IMAGE}
	fi
	copy_file	NAND-empty-1MB.jffs2			${WORKDIR}	${DEPLOY_DIR_IMAGE}
	copy_file	mk-sd-card.sh				${WORKDIR}	${DEPLOY_DIR_IMAGE}
	copy_file	write-to-SD-card.sh			${WORKDIR}	${DEPLOY_DIR_IMAGE}
	copy_file	fixboot.py				${WORKDIR}	${DEPLOY_DIR_IMAGE}
#	copy_file						${WORKDIR}	${DEPLOY_DIR_IMAGE}

	install		-d 	${D}/home/root
	copy_file	mk-sd-card.sh				${D}/home/root	${DEPLOY_DIR_IMAGE}
}
