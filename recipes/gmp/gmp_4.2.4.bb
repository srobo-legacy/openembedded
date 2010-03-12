SECTION = "libs"
DESCRIPTION = "GNU multiprecision arithmetic library"
HOMEPAGE = "http://www.swox.com/gmp/"
LICENSE = "GPL LGPL"

SRC_URI = "${GNU_MIRROR}/gmp/gmp-${PV}.tar.bz2;name=gmp;name=gmp424tarbz2 \
	   file://configure.patch;patch=1 \
	   file://amd64.patch;patch=1"
SRC_URI[gmp424tarbz2.md5sum] = "fc1e3b3a2a5038d4d74138d0b9cf8dbe"
SRC_URI[gmp424tarbz2.sha256sum] = "5420b0e558a69a53b36f2b2c70a69f547e075d98366a585fc80cbbcce1efe368"

INC_PR = "r0"
PR = "${INC_PR}.5"

SRC_URI_append += "file://sh4-asmfix.patch;patch=1 \
                   file://use-includedir.patch;patch=1 \
                   file://dont_use_mips_h_constraint.patch;patch=1 \
		  "

inherit autotools 

ARM_INSTRUCTION_SET = "arm"

acpaths = ""

LICENSE = "GPLv3 LGPLv3"

BBCLASSEXTEND="native"
