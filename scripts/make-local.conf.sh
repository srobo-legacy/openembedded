#!/bin/sh

TOPDIR=`pwd`

SED="sed -i"
CONF_FILE=local.conf.sample
OE_CONF_DIR=${TOPDIR}/openembedded/conf

source	environment.sh

OE_CONF_DIR=${TOPDIR}/openembedded/conf
CONF_DIR=${TOPDIR}/boards/build-$1/conf
SED="sed -i"
CONF_FILE=local.conf.sample
CONF_SRC=${OE_CONF_DIR}/${CONF_FILE}
CONF_PATCH=${OE_CONF_DIR}/${CONF_FILE}.patch


function edit_conf ()
{
	echo MACHINE=${MACHINE}
	${SED}	"s#MYMACHINE#${MACHINE}#g" $1.conf
	echo KERNEL=${KERNEL}
	${SED} 	"s#MYKERNEL#${KERNEL}#g" $1.conf
	echo TOPDIR=${TOPDIR}
	${SED} 	"s#MYCURDIR#${TOPDIR}#g" $1.conf
	echo DOWNLOAD=${DOWNLOAD}
	${SED} 	"s#MYDOWNLOAD#${DOWNLOAD}#g" $1.conf
	echo MAKEJOBS=${MAKEJOBS}
	${SED} 	"s#MYMAKEJOBS#${MAKEJOBS}#g" $1.conf
	echo THREADS=${THREADS}
	${SED} 	"s#MYTHREADS#${THREADS}#g" $1.conf
}



function	build_board ()
{
	MACHINE=$1
	CONF_DIR=${TOPDIR}/boards/build-$1/conf
	CONF_SRC=${OE_CONF_DIR}/${CONF_FILE}
	CONF_PATCH=${OE_CONF_DIR}/${CONF_FILE}.patch
	mkdir	-p	${CONF_DIR}

	cp		${CONF_SRC} ${CONF_DIR}
	cd		${CONF_DIR}
	cat		${CONF_PATCH}	| patch
	mv		${CONF_FILE} ${MACHINE}.conf
	ln	-s 	$1.conf local.conf

	edit_conf ${MACHINE}
}

if "x$1" == "x" ; then 
	source board_list.sh
else
	build_board $1
fi

