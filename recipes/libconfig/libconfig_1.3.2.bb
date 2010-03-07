DESCRIPTION = "Library for manipulating structured configuration files"
AUTHOR = "Mark Lindner"
HOMEPAGE = "http://www.hyperrealm.com/libconfig/"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "LGPLv2"
PR = "r0"

SRC_URI = "http://www.hyperrealm.com/libconfig/libconfig-${PV}.tar.gz"

S = "${WORKDIR}/libconfig-${PV}"

inherit autotools

