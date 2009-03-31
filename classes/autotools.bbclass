inherit autotools_base

def autotools_dep_prepend(d):
	import bb;

	if bb.data.getVar('INHIBIT_AUTOTOOLS_DEPS', d, 1):
		return ''

	pn = bb.data.getVar('PN', d, 1)
	deps = ''

	if pn in ['autoconf-native', 'automake-native']:
		return deps
	deps += 'autoconf-native automake-native '

	if not pn in ['libtool', 'libtool-native', 'libtool-cross']:
		deps += 'libtool-native '
		if not bb.data.inherits_class('native', d) \
                        and not bb.data.inherits_class('cross', d) \
                        and not bb.data.getVar('INHIBIT_DEFAULT_DEPS', d, 1):
                    deps += 'libtool-cross '

	return deps

DEPENDS_prepend = "${@autotools_dep_prepend(d)}"
acpaths = "default"
EXTRA_AUTORECONF = "--exclude=autopoint"

autotools_do_configure() {
	case ${PN} in
	autoconf*)
	;;
	automake*)
	;;
	*)
		# WARNING: gross hack follows:
		# An autotools built package generally needs these scripts, however only
		# automake or libtoolize actually install the current versions of them.
		# This is a problem in builds that do not use libtool or automake, in the case
		# where we -need- the latest version of these scripts.  e.g. running a build
		# for a package whose autotools are old, on an x86_64 machine, which the old
		# config.sub does not support.  Work around this by installing them manually
		# regardless.
		( for ac in `find ${S} -name configure.in -o -name configure.ac`; do
			rm -f `dirname $ac`/configure
			done )
		if [ -e ${S}/configure.in -o -e ${S}/configure.ac ]; then
			olddir=`pwd`
			cd ${S}
			if [ x"${acpaths}" = xdefault ]; then
				acpaths=
				for i in `find ${S} -maxdepth 2 -name \*.m4|grep -v 'aclocal.m4'| \
					grep -v 'acinclude.m4' | sed -e 's,\(.*/\).*$,\1,'|sort -u`; do
					acpaths="$acpaths -I $i"
				done
			else
				acpaths="${acpaths}"
			fi
			AUTOV=`automake --version |head -n 1 |sed "s/.* //;s/\.[0-9]\+$//"`
			install -d ${STAGING_DATADIR}/aclocal
			install -d ${STAGING_DATADIR}/aclocal-$AUTOV
			acpaths="$acpaths -I ${STAGING_DATADIR_NATIVE}/autoconf -I ${STAGING_DATADIR_NATIVE}/aclocal -I ${STAGING_DATADIR}/aclocal-$AUTOV -I ${STAGING_DATADIR}/aclocal"
			if [ -x ${STAGING_DATADIR_NATIVE}/libtool ]; then
				acpaths="$acpaths -I ${STAGING_DATADIR_NATIVE}/libtool"
			fi
			export AC_MACRODIR=${STAGING_DATADIR_NATIVE}/autoconf
			export AUTOM4TE="${STAGING_BINDIR_NATIVE}/autom4te"
			export ACLOCAL="${STAGING_BINDIR_NATIVE}/aclocal --acdir ${STAGING_DATADIR_NATIVE}/aclocal-$AUTOV"
			#export ACLOCAL="AUTOM4TE=\"${STAGING_BINDIR_NATIVE}/autom4te -B ${STAGING_DATADIR_NATIVE}/autoconf\" aclocal --acdir ${STAGING_DATADIR_NATIVE}/aclocal-$AUTOV"
			export AUTOCONF="${STAGING_BINDIR_NATIVE}/autoconf -I ${STAGING_DATADIR_NATIVE}/autoconf"
			export AUTOMAKE="${STAGING_BINDIR_NATIVE}/automake --libdir=${STAGING_DATADIR_NATIVE}/automake-$AUTOV"
			export AUTOHEADER="${STAGING_BINDIR_NATIVE}/autoheader"
			export perllibdir="${STAGING_DATADIR_NATIVE}/automake-$AUTOV"
			if [ -x ${STAGING_BINDIR_NATIVE}/m4 ]; then
				export M4=${STAGING_BINDIR_NATIVE}/m4
			fi

			# autoreconf is too shy to overwrite aclocal.m4 if it doesn't look
			# like it was auto-generated.  Work around this by blowing it away
			# by hand, unless the package specifically asked not to run aclocal.
			if ! echo ${EXTRA_AUTORECONF} | grep -q "aclocal"; then
				rm -f aclocal.m4
			fi

			subdirs="`autoconf -t AC_CONFIG_SUBDIRS|cut -d: -f4`"
			for dir in $subdirs .; do
				if [ ! -e "${S}/$dir" ]; then
					continue
				fi

				cd ${S}/$dir
				if [ -n "`autoconf -t AM_GLIB_GNU_GETTEXT`" ]; then
					oenote Executing glib-gettextize --force --copy
					echo "no" | glib-gettextize --force --copy
				fi
				if [ -n "`autoconf -t AC_PROG_INTLTOOL -t IT_PROG_INTLTOOL`" ]; then
					oenote Executing intltoolize --copy --force --automake
					intltoolize --copy --force --automake
				fi
				gnu_configize_here
			done

			oenote Executing autoreconf --install ${EXTRA_AUTORECONF} $acpaths
			autoreconf --install ${EXTRA_AUTORECONF} $acpaths || oefatal "autoreconf execution failed."
			cd $olddir
		fi
	;;
	esac
	if [ -e ${S}/configure ]; then
		oe_runconf $@
	else
		oenote "nothing to configure"
	fi
}

EXPORT_FUNCTIONS do_configure
