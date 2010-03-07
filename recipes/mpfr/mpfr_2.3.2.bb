require mpfr.inc

DEPENDS = "gmp"
PR = "r0"
SRC_URI = "http://www.mpfr.org/mpfr-${PV}/mpfr-${PV}.tar.bz2;name=mpfr232tarbz2"
SRC_URI[mpfr232tarbz2.md5sum] = "527147c097874340cb9cee0579dacf3b"
SRC_URI[mpfr232tarbz2.sha256sum] = "18e078c996e182b7ceab32f2ab840e6a151b593e0cd5b83cb9d2960f212fba4c"
S = "${WORKDIR}/mpfr-${PV}"

BBCLASSEXTEND="native"
