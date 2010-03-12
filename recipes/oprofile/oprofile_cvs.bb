require oprofile.inc

PV = "0.9.4+cvs${SRCDATE}"
PR = "r3"

RDEPENDS += "binutils-symlinks"

SRC_URI = "cvs://anonymous@oprofile.cvs.sourceforge.net/cvsroot/oprofile;module=oprofile \
           file://opstart.patch;patch=1 \
	   file://acinclude.m4"

S = "${WORKDIR}/oprofile"
