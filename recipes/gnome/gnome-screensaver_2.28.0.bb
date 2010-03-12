DESCRIPTION = "GNOME utilities"
SECTION = "x11/gnome"
LICENSE = "GPL"
DEPENDS = "gnome-common glib-2.0 gtk+ gconf"

inherit gnome pkgconfig

PR = "r1"

EXTRA_OECONF = " \
                --with-shadow=${STAGING_INCDIR}/.. \
               "

do_configure_prepend () {
	cp ${STAGING_DATADIR}/gnome-common/data/omf.make ${S}
	sed -i -e 's:$CPPFLAGS -I$includedir:$CPPFLAGS:g' configure.ac
}

FILES_${PN} += "${datadir}/*background* ${datadir}/desktop-directories"

FILES_${PN}-dbg += "${libexecdir}/gnome-screensaver/.debug "


