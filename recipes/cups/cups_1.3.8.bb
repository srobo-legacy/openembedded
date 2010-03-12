require cups.inc

SRC_URI += "file://use_echo_only_in_init.patch;patch=1"
PR = "${INC_PR}.2"

EXTRA_OECONF += " --disable-gssapi --disable-ssl"
