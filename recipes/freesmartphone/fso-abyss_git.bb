DESCRIPTION = "Abyss is a GSM 07.10 muxer userspace daemon"
HOMEPAGE = "http://www.freesmartphone.org/mediawiki/index.php/Implementations/Abyss"
AUTHOR = "Michael 'Mickey' Lauer <mlauer@vanille-media.de>"
SECTION = "console/network"
DEPENDS = "vala-native dbus dbus-glib libgsm0710mux"
LICENSE = "GPL"
SRCREV = "4d7edda0efec7836612c8c64bb1b90695ca07a53"
PV = "0.9.2+gitr${SRCREV}"
PE = "1"
PR = "r0"

SRC_URI = "\
  ${FREESMARTPHONE_GIT}/cornucopia.git;protocol=git;branch=master \
  file://fso-abyss.conf \
"
S = "${WORKDIR}/git/tools/fso-abyss"

inherit autotools_stage

do_install_append() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/fso-abyss.conf ${D}${sysconfdir}/
}

PACKAGES =+ "${PN}-config"

FILES_${PN} += "${sysconfdir} ${datadir}"

RRECOMMENDS_${PN} = "${PN}-config"

FILES_${PN}-config = "\
  ${sysconfdir}/fso-abyss.conf \
"

CONFFILES_${PN}-config = "\
  ${sysconfdir}/fso-abyss.conf \
"

PACKAGE_ARCH_${PN} = "${BASE_PACKAGE_ARCH}"
