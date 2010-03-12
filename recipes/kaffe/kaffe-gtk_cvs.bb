
PV = "1.1.5+cvs${SRCDATE}"
SRC_URI = "cvs://readonly:readonly@cvs.kaffe.org/cvs/kaffe;module=kaffe"
S = "${WORKDIR}/kaffe"

require kaffe.inc

DEPENDS += "glib-2.0 gmp gtk+ pango zlib libxtst kaffeh-native"

EXTRA_OECONF += ""
