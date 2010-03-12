DESCRIPTION = "A C++ Wrapper for the directfb framebuffer library."
HOMEPAGE = "http://directfb.org"
SECTION = "libs"
DEPENDS = "directfb"
LICENSE = "LGPL"

SRC_URI = "http://www.directfb.org/downloads/Extras/++DFB-${PV}.tar.gz"
S = "${WORKDIR}/++DFB-${PV}"
RV = "0.9-25"

inherit autotools pkgconfig

FILES_++dfb_append = " ${libdir}/*.so*"
