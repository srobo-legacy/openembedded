require dtc.inc

PR = "r0"

SRC_URI = "http://www.jdl.com/software/dtc-v${PV}.tgz"

S = "${WORKDIR}/dtc-v${PV}"


BBCLASSEXTEND="native"
