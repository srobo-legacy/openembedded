PV = "0.0+cvs${SRCDATE}"
SECTION = "libs"
LICENSE = "LGPL"
PRIORITY = "optional"
DEPENDS = "libsvg cairo"
DESCRIPTION = "SVG rendering library"
SRC_URI = "cvs://anoncvs:anoncvs@cvs.cairographics.org/cvs/cairo;module=libsvg-cairo"
S = "${WORKDIR}/libsvg-cairo"

inherit autotools pkgconfig
