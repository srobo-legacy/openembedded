require dates.inc

PV = "0.4.5+svnr${SRCPV}"
S = "${WORKDIR}/trunk"

SRC_URI = "svn://svn.o-hand.com/repos/${PN};module=trunk;proto=http \
	   file://dates-owl-window-menu.patch;patch=1 \
	  "
