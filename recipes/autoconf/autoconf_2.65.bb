require autoconf.inc

PR = "r2"

PARALLEL_MAKE = ""

DEFAULT_PREFERENCE= "-1"

DEPENDS += "m4-native"
RDEPENDS_${PN} = "m4 gnu-config"

SRC_URI += "file://autoreconf-include.patch;patch=1 \
	   file://autoreconf-exclude.patch;patch=1 \
	   file://autoreconf-foreign.patch;patch=1 \
	   file://autoreconf-gnuconfigize.patch;patch=1 \
	   file://autoheader-nonfatal-warnings.patch;patch=1 \
	   ${@['file://path_prog_fixes.patch;patch=1', ''][bb.data.inherits_class('native', d)]} \
           file://config_site.patch;patch=1"
SRC_URI[autoconf.md5sum] = "a6de1cc6434cd64038b0a0ae4e252b33"
SRC_URI[autoconf.sha256sum] = "db11944057f3faf229ff5d6ce3fcd819f565455c152b72cec17ebc1cbb80136b"

DEPENDS_virtclass-native = "m4-native gnu-config-native"
RDEPENDS_${PN}_virtclass-native = "m4-native gnu-config-native"

SRC_URI_append_virtclass-native = " file://fix_path_xtra.patch;patch=1"

BBCLASSEXTEND = "native"
