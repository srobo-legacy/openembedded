require ${PN}.inc
PV = "${OPIE_GIT_PV}"
PR = "r19"

SRC_URI = "${OPIE_GIT};protocol=git;subpath=core/apps/calibrate \
           ${OPIE_GIT};protocol=git;subpath=noncore/settings/mediummount \
           ${OPIE_GIT};protocol=git;subpath=core/launcher \
           ${OPIE_GIT};protocol=git;subpath=pics \
           ${OPIE_GIT};protocol=git;subpath=apps \
           ${OPIE_GIT};protocol=git;subpath=root \
           ${OPIE_GIT};protocol=git;subpath=etc \
           file://nomax.patch;patch=1;pnum=3 \
           file://no-builtin-qss-startup-2.patch;patch=1 \
           file://kbdlocks-runtime.patch;patch=1 \
	   file://restart-from-bindir.patch;patch=1 \
           file://server-pro-cvs.patch;patch=1 \
	   file://firstuse-path.patch;patch=1 \
           file://03opiesignal \
          "

do_install_append() {
	install -d ${D}${bindir} ${D}${sysconfdir}/apm/event.d/
	install -m 0755 ${WORKDIR}/03opiesignal ${D}${sysconfdir}/apm/event.d/
}

