require ${PN}.inc

PV = "${OPIE_GIT_PV}"
PR = "r5"

SRC_URI = "${OPIE_GIT};protocol=git;subpath=inputmethods/multikey \
           ${OPIE_GIT};protocol=git;subpath=share \
           file://fix-rpath.patch;patch=1"

#           file://friendly-button-names.patch;patch=1"
