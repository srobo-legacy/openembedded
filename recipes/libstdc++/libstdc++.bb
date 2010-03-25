PR = "r1"
LIBSTDCPLUSPLUS_TARGET = ${STAGING_DIR_TARGET}/lib

do_install () {
	if ! [ -e ${LIBSTDCPLUSPLUS_TARGET}/libstdc++.so ] ; then
		cp ${CROSS_DIR}/${TARGET_SYS}/lib/libstdc++.so.6.*	${LIBSTDCPLUSPLUS_TARGET}
		cd ${LIBSTDCPLUSPLUS_TARGET}
		file=`ls libstdc++.so.6.*`
		ln	-s $file	libstdc++.so.6
		ln	-s $file	libstdc++.so
	fi
}

PROVIDES = "libstdc++"

