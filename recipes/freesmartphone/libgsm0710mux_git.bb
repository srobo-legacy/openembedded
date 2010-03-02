DESCRIPTION = "A GSM 07.10 Multiplexing Engine"
AUTHOR = "Michael 'Mickey' Lauer <mlauer@vanille-media.de>"
LICENSE = "GPL"
SECTION = "devel"
DEPENDS = "glib-2.0 libgsm0710 libfsotransport"
PV = "0.9.0+gitr${SRCPV}"
PE = "1"
PR = "r0"

SRC_URI = "\
  ${FREESMARTPHONE_GIT}/libgsm0710mux;protocol=git;branch=master \
"
S = "${WORKDIR}/git"

inherit autotools_stage pkgconfig vala
