DESCRIPTION = "the X.Org X-kdrive server"
require xserver-kdrive-common.inc

DEPENDS += "hal libxkbfile libxcalibrate pixman"
RDEPENDS += "hal"

DEFAULT_PREFERENCE = "-99" 

PE = "1"
PR = "r1"



SRC_URI += "file://sysroot_fix.patch;patch=1 \
	    file://dolt-fix.patch;patch=1 \
	    file://xorg-server-1.6.1-xcalibrate.patch;patch=1 \
	    file://xorg-server-1.6.1-tslib.patch;patch=1 \
	    file://xorg-avr32-support.diff;patch=1 \
	"

S = "${WORKDIR}/xorg-server-${PV}"

MESA_VER = "7.2"

export LDFLAGS += " -ldl "

EXTRA_OECONF += "--enable-builtin-fonts \
	"

#	--enable-tslib \
#	--enable-dri \
#	--enable-xorgcfg \

