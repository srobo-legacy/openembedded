require gpe-ownerinfo.inc

PR = "r1"
PV = "0.28+svn-${SRCDATE}"

SRC_URI += "${GPE_SVN} \
	    file://svn-build.patch;patch=1" 

S = "${WORKDIR}/${PN}"
