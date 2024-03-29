DESCRIPTION = "Blueman is a GTK+ Bluetooth Manager"
HOMEPAGE = "http://www.blueman-project.org/"
SECTION = "optional"
DEPENDS = "gtk+ glib-2.0 bluez4 intltool python python-native python-pyrex startup-notification"
RDEPENDS = "python-dbus python-pygobject python dbus bluez4 python-pygtk python-notify obex-data-server \
"

PR = "r1"

inherit autotools pkgconfig python-dir

EXTRA_OECONF += "--with-no-runtime-deps-check"

BLUEZ_LIBS = "-L${libdir} -lbluetooth"
BLUEZ_CFLAGS = "-I${includedir}"

SRC_URI = "\
	http://download.tuxfamily.org/blueman/blueman-${PV}.tar.gz\
	"

FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/.debug"
FILES_${PN} += "${libdir} ${datadir}"

do_configure_prepend() {
		sed -i "s/py_prefix=.*$/py_prefix=\"${@"${STAGING_DIR_TARGET}".replace("/","\/")}\/usr\"/" ${S}/acinclude.m4
		sed -i "s/py_exec_prefix=.*$/py_exec_prefix=\"${@"${STAGING_DIR_TARGET}".replace("/","\/")}\/usr\"/" ${S}/acinclude.m4
}
