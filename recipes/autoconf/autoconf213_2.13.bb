require autoconf.inc
SRC_URI = "${GNU_MIRROR}/autoconf/autoconf-${PV}.tar.gz;name=autoconf263tarbz2"
SRC_URI[autoconf263tarbz2.md5sum] = "7565809ed801bb5726da0631ceab3699"
SRC_URI[autoconf263tarbz2.sha256sum] = "264d7c1c0e268bc77fbe0f308e085545535edfe73f33e27c80219cc0c9c71246"
EXTRA_OECONF = "--program-transform-name=s/\$/2.13/"
EXTRA_OEMAKE = 'acdatadir="${datadir}/autoconf-${PV}" infodir="${datadir}/autoconf-${PV}/info"'
PR = "${INC_PR}.0"

BBCLASSEXTEND="native"
