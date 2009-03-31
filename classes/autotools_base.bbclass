inherit base

# use autotools_stage_all for native packages
AUTOTOOLS_NATIVE_STAGE_INSTALL = "1"

EXTRA_OEMAKE = ""
DEPENDS_prepend = "${@['gnu-config-native ', ''][bool(d.getVar('INHIBIT_AUTOTOOLS_DEPS', 1))]}"

def autotools_set_crosscompiling(d):
	import bb
	if not bb.data.inherits_class('native', d):
		return " cross_compiling=yes"
	return ""

# EXTRA_OECONF_append = "${@autotools_set_crosscompiling(d)}"

oe_runconf () {
	if [ -x ${S}/configure ] ; then
		cfgcmd="${S}/configure \
		    --build=${BUILD_SYS} \
		    --host=${HOST_SYS} \
		    --target=${TARGET_SYS} \
		    --prefix=${prefix} \
		    --exec_prefix=${exec_prefix} \
		    --bindir=${bindir} \
		    --sbindir=${sbindir} \
		    --libexecdir=${libexecdir} \
		    --datadir=${datadir} \
		    --sysconfdir=${sysconfdir} \
		    --sharedstatedir=${sharedstatedir} \
		    --localstatedir=${localstatedir} \
		    --libdir=${libdir} \
		    --includedir=${includedir} \
		    --oldincludedir=${oldincludedir} \
		    --infodir=${infodir} \
		    --mandir=${mandir} \
			${EXTRA_OECONF} \
		    $@"
		oenote "Running $cfgcmd..."
		$cfgcmd || oefatal "oe_runconf failed" 
	else
		oefatal "no configure script found"
	fi
}

gnu_configize_here() {
	if [ -n "`autoconf -t AC_CANONICAL_BUILD`" ]; then
		macrodir="`autoconf -t AC_CONFIG_MACRO_DIR|cut -d: -f4`"
		if [ -z "$macrodir" ]; then
			macrodir="."
		fi
		if [ ! -e ${STAGING_DATADIR_NATIVE}/gnu-config/config.sub ]; then
			oefatal "gnu-config data files missing"
		fi
		rm -f $macrodir/config.sub $macrodir/config.guess
		cp -f ${STAGING_DATADIR_NATIVE}/gnu-config/config.sub $macrodir/config.sub
		cp -f ${STAGING_DATADIR_NATIVE}/gnu-config/config.guess $macrodir/config.guess
	fi
}

gnu_configize() {
	olddir="$PWD"
	subdirs="`autoconf -t AC_CONFIG_SUBDIRS|cut -d: -f4`"
	for dir in $subdirs .; do
		if [ ! -e "$olddir/$dir" ]; then
			continue
		fi

		cd $olddir/$dir
		gnu_configize_here
	done
}

do_configure_prepend () {
	alias gnu-configize=gnu_configize
}

autotools_base_do_configure() {
	if [ -e ${S}/configure ]; then
		gnu_configize
		oe_runconf $@
	else
		oenote "nothing to configure"
	fi
}

autotools_base_do_install() {
	oe_runmake 'DESTDIR=${D}' install
}

do_install_append() {
	for i in `find ${D} -name "*.la"` ; do \
		sed -i -e '/^dependency_libs=/s,${WORKDIR}[[:alnum:]/\._+-]*/\([[:alnum:]\._+-]*\),${libdir}/\1,g' $i
		sed -i -e s:${CROSS_DIR}/${HOST_SYS}::g $i
		sed -i -e s:${CROSS_DIR}::g $i
		sed -i -e s:${STAGING_LIBDIR}:${libdir}:g $i
		sed -i -e s:${STAGING_DIR_HOST}::g $i
		sed -i -e s:${STAGING_DIR}::g $i
		sed -i -e s:${S}::g $i
		sed -i -e s:${T}::g $i
		sed -i -e s:${D}::g $i
	done
}

STAGE_TEMP="${WORKDIR}/temp-staging"

autotools_stage_includes() {
	if [ "${INHIBIT_AUTO_STAGE_INCLUDES}" != "1" ]
	then
		rm -rf ${STAGE_TEMP}
		mkdir -p ${STAGE_TEMP}
		make DESTDIR="${STAGE_TEMP}" install
		cp -pPR ${STAGE_TEMP}/${includedir}/* ${STAGING_INCDIR}
		rm -rf ${STAGE_TEMP}
	fi
}

autotools_stage_dir() {
	from="$1"
	to="$2"
	# This will remove empty directories so we can ignore them
	rmdir "$from" 2> /dev/null || true
	if [ -d "$from" ]; then
		mkdir -p "$to"
		cp -fpPR "$from"/* "$to"
	fi
}

autotools_stage_all() {
	if [ "${INHIBIT_AUTO_STAGE}" = "1" ]
	then
		return
	fi
	rm -rf ${STAGE_TEMP}
	mkdir -p ${STAGE_TEMP}
	oe_runmake DESTDIR="${STAGE_TEMP}" install
	autotools_stage_dir ${STAGE_TEMP}/${includedir} ${STAGING_INCDIR}
	if [ "${BUILD_SYS}" = "${HOST_SYS}" ]; then
		autotools_stage_dir ${STAGE_TEMP}/${bindir} ${STAGING_DIR_HOST}${layout_bindir}
		autotools_stage_dir ${STAGE_TEMP}/${sbindir} ${STAGING_DIR_HOST}${layout_sbindir}
		autotools_stage_dir ${STAGE_TEMP}/${base_bindir} ${STAGING_DIR_HOST}${layout_base_bindir}
		autotools_stage_dir ${STAGE_TEMP}/${base_sbindir} ${STAGING_DIR_HOST}${layout_base_sbindir}
		autotools_stage_dir ${STAGE_TEMP}/${libexecdir} ${STAGING_DIR_HOST}${layout_libexecdir}
		if [ "${prefix}/lib" != "${libdir}" ]; then
			# python puts its files in here, make sure they are staged as well
			autotools_stage_dir ${STAGE_TEMP}/${prefix}/lib ${STAGING_DIR_HOST}${layout_prefix}/lib
		fi
	fi
	if [ -d ${STAGE_TEMP}/${libdir} ]
	then
		olddir=`pwd`
		cd ${STAGE_TEMP}/${libdir}
		las=$(find . -name \*.la -type f)
		cd $olddir
		echo "Found la files: $las"		 
		for i in $las
		do
			sed -e 's/^installed=yes$/installed=no/' \
			    -e '/^dependency_libs=/s,${WORKDIR}[[:alnum:]/\._+-]*/\([[:alnum:]\._+-]*.la\),${STAGING_LIBDIR}/\1,g' \
			    -e '/^dependency_libs=/s,${WORKDIR}[[:alnum:]/\._+-]*/\([[:alnum:]\._+-]*\),${STAGING_LIBDIR},g' \
			    -e "/^dependency_libs=/s,\([[:space:]']\)${libdir},\1${STAGING_LIBDIR},g" \
			    -i ${STAGE_TEMP}/${libdir}/$i
		done
		autotools_stage_dir ${STAGE_TEMP}/${libdir} ${STAGING_LIBDIR}
	fi
	# Ok, this is nasty. pkgconfig.bbclass is usually used to install .pc files,
	# however some packages rely on the presence of .pc files to enable/disable
	# their configurataions in which case we better should not install everything
	# unconditionally, but rather depend on the actual results of make install.
	# The good news though: a) there are not many packages doing this and
	# b) packaged staging will fix that anyways. :M:
	if [ "${AUTOTOOLS_STAGE_PKGCONFIG}" = "1" ]
	then
		if [ -e ${STAGE_TEMP}/${libdir}/pkgconfig/ ] ; then
			echo "cp -f ${STAGE_TEMP}/${libdir}/pkgconfig/*.pc ${STAGING_LIBDIR}/pkgconfig/"
			cp -f ${STAGE_TEMP}/${libdir}/pkgconfig/*.pc ${STAGING_LIBDIR}/pkgconfig/
		fi
	fi
	rm -rf ${STAGE_TEMP}/${mandir} || true
	rm -rf ${STAGE_TEMP}/${infodir} || true
	autotools_stage_dir ${STAGE_TEMP}/${datadir} ${STAGING_DATADIR}
	rm -rf ${STAGE_TEMP}
}

EXPORT_FUNCTIONS do_configure do_install
