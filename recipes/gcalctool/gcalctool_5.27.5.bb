LICENSE = "GPL"
SECTION = "x11"
DEPENDS = "gtk+"
DESCRIPTION = "gcalctool is a powerful calculator"
PR = "r1"

inherit gnome

DEPENDS = "gnome-common gtk+ libgnomeui"

SRC_URI += " \
	file://gcalctool-5.27.5-host-pkg-config.patch;patch=1 \
	"

do_configure() {
	sed -i 's:-I$(includedir)::g' src/Makefile.am
	PKG_CONFIG_PATH=/usr/lib64/pkgconfig:/usr/share/pkgconfig \
	HOST_PKG_CONFIG=/usr/bin/pkg-config \
	oe_runconf
}

do_configure_prepend() {
	sed -i 's:-I$(includedir)::g' src/Makefile.am
}

do_stage() {
	autotools_stage_all
}

