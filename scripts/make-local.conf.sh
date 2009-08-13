#!/bin/sh
MACHINE=$1
TOPDIR=`pwd`
source	environment.sh

OE_CONF_DIR=${TOPDIR}/openembedded/conf
CONF_DIR=${TOPDIR}/build-$1/conf
SED="sed -i"
CONF_FILE=local.conf.sample
CONF_SRC=${OE_CONF_DIR}/${CONF_FILE}
CONF_PATCH=${OE_CONF_DIR}/${CONF_FILE}.patch


function edit ()
{
	sed -r -i -e $2 $1
}

mkdir	-p	${CONF_DIR}
cp		${CONF_SRC} ${CONF_DIR}
cd		${CONF_DIR}
cat		${CONF_PATCH}	| patch
mv		${CONF_FILE} $1.conf
ln	-s 	$1.conf local.conf


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

# Example local.conf

# DOWNLOAD=/usr/local/install/downloads
# MAKEJOBS=4
# THREADS=4
# KERNEL=2.6.30
