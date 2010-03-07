SECTION = "multimedia"
PRIORITY = "optional"
LICENSE = "LGPL"
HOMEPAGE = "http://www.gstreamer.net/"

inherit autotools pkgconfig

OE_LT_RPATH_ALLOW = "any"
OE_LT_RPATH_ALLOW[export]="1"

AUTOTOOLS_STAGE_PKGCONFIG = "1"

do_stage() {
	autotools_stage_all
}


