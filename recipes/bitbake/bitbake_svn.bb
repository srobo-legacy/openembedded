require bitbake.inc

PV = "1.9.0+svn${SRCDATE}"
PR = "r1"

SRC_URI = "svn://svn.berlios.de/bitbake/trunk;module=bitbake"
S = "${WORKDIR}/bitbake"
