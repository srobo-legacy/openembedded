DESCRIPTION = "An Embeddable SQL Database Engine"
HOMEPAGE = "http://www.sqlite.org/"
SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "readline ncurses"
LICENSE = "PD"
PR = "r4"

SRC_URI = "http://www.hwaci.com/sw/sqlite/sqlite-${PV}.tar.gz \
       file://mainmk_build_dynamic.patch;patch=1 \
       file://mainmk_no_tcl.patch;patch=1 \
       file://sqlite.pc"

SOURCES = "attach.o auth.o btree.o btree_rb.o build.o copy.o date.o delete.o \
           expr.o func.o hash.o insert.o main.o opcodes.o os.o pager.o \
           parse.o pragma.o printf.o random.o select.o table.o tokenize.o \
           trigger.o update.o util.o vacuum.o vdbe.o vdbeaux.o where.o"

inherit autotools pkgconfig

do_configure() {
    echo "main.mk is patched, no need to configure"
    # make pkgconfig.bbclass pick this up
    mv ${WORKDIR}/sqlite.pc ${S}
}

do_compile() {
	oe_runmake -f Makefile.linux-gcc \
                   TOP="${S}" \
                   BCC="${BUILD_CC}" \
                   TCC="${CC}" \
                   OPTS="-fPIC -D'INTPTR_TYPE=int'" \
                   TCL_FLAGS= LIBTCL= \
                   READLINE_FLAGS="-DHAVE_READLINE=1 -I${STAGING_INCDIR}" \
                   LIBREADLINE="-L. -L${STAGING_LIBDIR} -lreadline -lncurses"
}

do_stage() {
	install -m 0755 libsqlite.so ${STAGING_LIBDIR}/libsqlite.so.0.8.6
        ln -sf libsqlite.so.0.8.6 ${STAGING_LIBDIR}/libsqlite.so
        ln -sf libsqlite.so.0.8.6 ${STAGING_LIBDIR}/libsqlite.so.0
        ln -sf libsqlite.so.0.8.6 ${STAGING_LIBDIR}/libsqlite.so.0.8
        install -m 0644 sqlite.h ${STAGING_INCDIR}
}

do_install() {
	install -d ${D}${libdir} ${D}${bindir}
	install sqlite ${D}${bindir}
	install -m 0755 libsqlite.so ${D}${libdir}/libsqlite.so.0.8.6
	ln -sf libsqlite.so.0.8.6 ${D}${libdir}/libsqlite.so
        ln -sf libsqlite.so.0.8.6 ${D}${libdir}/libsqlite.so.0
        ln -sf libsqlite.so.0.8.6 ${D}${libdir}/libsqlite.so.0.8
        install -d ${D}${includedir}
        install -m 0644 sqlite.h ${D}${includedir}/sqlite.h
        install -d ${D}${libdir}/pkgconfig
        install -m 0644 ${S}/sqlite.pc ${D}${libdir}/pkgconfig/sqlite.pc
}

PACKAGES += "${PN}-bin"
FILES_${PN}-bin = "${bindir}/*"
FILES_${PN} = "${libdir}/*.so.*"
