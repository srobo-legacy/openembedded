DESCRIPTION = "Sugar datastore"
LICENSE = "GPLv2"

PR = "r0"

DEPENDS = "sugar-toolkit python-xappy "
RDEPENDS = "sugar-toolkit sugar-base python-xappy python-cjson xapian-bindings-python"

SRC_URI = "http://download.sugarlabs.org/sources/sucrose/glucose/sugar-datastore/${PN}-${PV}.tar.bz2"

inherit autotools

FILES_${PN} += "${datadir}/dbus-1"

