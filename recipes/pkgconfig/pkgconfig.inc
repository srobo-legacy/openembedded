SECTION = "console/utils"
DESCRIPTION = "pkg-config is a system for managing library \
compile/link flags that works with automake and autoconf. \
It replaces the ubiquitous *-config scripts you may have \
seen with a single tool."
HOMEPAGE = "http://pkg-config.freedesktop.org/wiki/"
LICENSE = "GPL"
INC_PR = "r7"

SRC_URI = "http://pkgconfig.freedesktop.org/releases/pkg-config-${PV}.tar.gz;name=pkgconfig023targz \
           file://autofoo.patch;patch=1 \
           file://sysrootfix.patch;patch=1 \
           file://glibconfig-sysdefs.h"
SRC_URI[pkgconfig023targz.md5sum] = "d922a88782b64441d06547632fd85744"
SRC_URI[pkgconfig023targz.sha256sum] = "08a0e072d6a05419a58124db864f0685e6ac96e71b2875bf15ac12714e983b53"

S = "${WORKDIR}/pkg-config-${PV}/"

inherit autotools

acpaths = "-I ."
do_configure_prepend () {
	install -m 0644 ${WORKDIR}/glibconfig-sysdefs.h glib-1.2.*/
}

do_stage() {
	install -d -m 0755 ${STAGING_LIBDIR}/pkgconfig
}
