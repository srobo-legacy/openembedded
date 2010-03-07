DESCRIPTION = "GNU Project parser generator (yacc replacement)."
HOMEPAGE = "http://www.gnu.org/software/bison/"
LICENSE = "GPL"
SECTION = "devel"
PRIORITY = "optional"
DEPENDS = "gettext"

SRC_URI = "${GNU_MIRROR}/bison/bison-${PV}.tar.gz;name=bison23targz \
	   file://m4.patch;patch=1"
SRC_URI[bison23targz.md5sum] = "22327efdd5080e2b1acb6e560a04b43a"
SRC_URI[bison23targz.sha256sum] = "52f78aa4761a74ceb7fdf770f3554dd84308c3b93c4255e3a5c17558ecda293e"

INCPR = "r4"

inherit autotools

NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"

PR = "${INCPR}.0"

# >> bison-2.3-r0: /usr/lib/liby.a
# That one is a special case: it wants to stay in the main bison package,
# since bison itself is a development tool.  I'm not sure why it is a
# static-only library; that might be an error.

FILES_${PN} += "${libdir}/liby.a"
