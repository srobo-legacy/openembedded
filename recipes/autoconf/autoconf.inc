DESCRIPTION = "A package of M4 macros to produce scripts to \
automatically configure sourcecode."
LICENSE = "GPLv3"
HOMEPAGE = "http://www.gnu.org/software/autoconf/"
SECTION = "devel"
DEPENDS += "m4-native"
RDEPENDS_${PN} = "m4 perl gnu-config"
DEPENDS_virtclass-native = "m4-native gnu-config-native"
RDEPENDS_${PN}_virtclass-native = "m4-native gnu-config-native"

INC_PR = "r10"

SRC_URI = "${GNU_MIRROR}/autoconf/autoconf-${PV}.tar.bz2;name=autoconf \
	   file://program_prefix.patch;patch=1"

inherit autotools

PARALLEL_MAKE = ""
BBCLASSEXTEND = "native"
