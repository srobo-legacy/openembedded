DESCRIPTION = "Qt/Embedded Version ${PV}"
SECTION = "libs"
PRIORITY = "optional"
HOMEPAGE = "http://www.trolltech.com"
LICENSE = "GPL"
DEPENDS = "zlib libpng jpeg tslib uicmoc-native"
#DEPENDS_append_c7x0 = " sharp-aticore-oss"
PROVIDES = "virtual/libqte2"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/qte-${PV}"

# Workaround GCC 4.1 issues with -fvisibility-inlines-hidden (see #1521)
# by disabling it for qpe only. TODO: pinpoint actual issue causing GCC 
# misbehavior.
#
# Laibsch: Remove -fno-visibility-inlines-hidden instead of negating it
# Laibsch: Fixes bug 1631
#CXXFLAGS += "-fno-visibility-inlines-hidden"
CXXFLAGS := "${@oe_filter_out('-fvisibility-inlines-hidden', '${CXXFLAGS}', d)}"

SRC_URI = "ftp://ftp.trolltech.com/pub/qt/source/qt-embedded-${PV}-free.tar.gz;md5sum=1f7ad30113afc500cab7f5b2f4dec0d7 \
   	   file://qpe.patch;patch=1 \
	   file://vt-switch.patch;patch=1 \
	   file://daemonize.patch;patch=1 \
	   file://no-moc.patch;patch=1 \
	   file://gcc3.patch;patch=1 \
	   file://gcc4.patch;patch=1 \
	   file://encoding.patch;patch=1 \
	   file://fix-qgfxraster.patch;patch=1 \
	   file://qt-visibility.patch;patch=1 \
	   file://tslib.patch;patch=1 \
	   file://handhelds.patch;patch=1 \
	   file://qiconview-speed.patch;patch=1 \
	   file://qtabbar.patch;patch=1 \
	   file://increase-qxml-robustness.patch;patch=1 \
	   file://qte-fix-iconsize.patch;patch=1 \
	   file://fix-linuxfb-setmode.patch;patch=1 \
	   file://fix-linuxfb-offscreenoverflow.patch;patch=1 \
	   file://fix-qscreen-sync.patch;patch=1 \
	   file://improve-calibration-r0.patch;patch=1 \
	   file://key.patch;patch=1 \
	   file://bidimetrics.patch;patch=5 \
	   file://fix-native-build.patch;patch=1 \
	   file://fix-errno-exception-spec.patch;patch=1 \
           file://keyboardless-buttonmap.patch;patch=1 \
           file://kernel-keymap.patch;patch=1 \
	   file://kernel-keymap-corgi.patch;patch=1 \
	   file://remove-unused-kbdhandler.patch;patch=1 \
	   file://disable-dup-rotation.patch;patch=1 \
	   file://fix-qte-asm-include.patch;patch=1 \
	   file://sharp_char.h \
	   file://switches.h "

#
# add device specific patches here
#

W100_ACCEL_PATCHES = "file://c7x0-w100-accel.patch;patch=1 \
                      file://c7x0-w100-gcc4.patch;patch=1 \
		      file://suspend-resume-hooks.patch;patch=1"

#SRC_URI_append_c7x0         = "${W100_ACCEL_PATCHES} "
#SRC_URI_append_spitz        = "file://spitz-adhoc-keypad-rotate.patch;patch=1 "
#SRC_URI_append_akita        = "file://spitz-adhoc-keypad-rotate.patch;patch=1 "

# "ipaqs" used to have this, but later were proven to at least work
# without it. Review again and see if has interesting bits to be applied
# universally.
#SRC_URI_append   = "file://ipaq_sound_fix.patch;patch=1 "


S = "${WORKDIR}/qt-${PV}"
export QTDIR = "${S}"

require qte-functions.inc
QTE_ARCH := "${@qte_arch(d)}"

#
# add device configuration here
#
EXTRA_OECONF_CONFIG = "-qconfig qpe"
#EXTRA_OECONF_CONFIG_c7x0 = "-qconfig qpe -accel-w100"
EXTRA_OECONF_CONFIG_native = "-qconfig qpe -qvfb"

EXTRA_OECONF = "-system-jpeg -system-libpng -system-zlib -no-qvfb -no-xft -no-vnc -gif \
		-xplatform ${TARGET_OS}-${QTE_ARCH}-g++ ${EXTRA_OECONF_CONFIG} -depths 8,16,32"
EXTRA_OEMAKE = "-e"

#
# add device flags here
#
EXTRA_DEFINES 			= "-DQT_QWS_TSLIB -DQT_QWS_CUSTOM -DQT_QWS_IPAQ"
#EXTRA_DEFINES_spitz		= "-DQT_QWS_TSLIB -DQT_QWS_CUSTOM -DQT_QWS_IPAQ -DQT_QWS_SLCXK"
#EXTRA_DEFINES_akita             = "-DQT_QWS_TSLIB -DQT_QWS_CUSTOM -DQT_QWS_IPAQ -DQT_QWS_SLCXK"

#
# don't touch anything below here
#
export SYSCONF_CC = "${CC}"
export SYSCONF_CXX = "${CXX}"
export SYSCONF_LINK = "${CCLD}"
export SYSCONF_SHLIB = "${CCLD}"
export SYSCONF_CFLAGS = "${CFLAGS}"
export SYSCONF_LINK_SHLIB = "${CCLD}"
export SYSCONF_CXXFLAGS = "${CXXFLAGS} -pipe -DQWS -fno-exceptions -frtti -DNO_DEBUG ${EXTRA_DEFINES} -DUSE_BIDI"
# enable if gcc supports visibility
#export SYSCONF_CXXFLAGS = "${CXXFLAGS} -pipe -DQWS -fno-exceptions -frtti -fvisibility=hidden -DGCC_SUPPORTS_VISIBILITY -DNO_DEBUG ${EXTRA_DEFINES} -DUSE_BIDI"
export SYSCONF_LFLAGS = "${LDFLAGS} -lts"
export SYSCONF_MOC = "${STAGING_BINDIR_NATIVE}/moc"
export SYSCONF_UIC = "${STAGING_BINDIR_NATIVE}/uic"

# generate uclibc and eabi configurations
do_configure() {
	for f in ${S}/configs/linux-*-g++-shared; do
		sed -e 's,-linux-,-linux-uclibc-,g' < $f \
			> `dirname $f`/`basename $f | sed -e 's,linux-,linux-uclibc-,'`
		sed -e 's,-linux-,-linux-gnueabi-,g' < $f \
			> `dirname $f`/`basename $f | sed -e 's,linux-,linux-gnueabi-,'`
		sed -e 's,-linux-,-linux-uclibceabi-,g' < $f \
			> `dirname $f`/`basename $f | sed -e 's,linux-,linux-uclibceabi-,'`
	done
	echo yes | ./configure ${EXTRA_OECONF} || die "Configuring qte failed. EXTRA_OECONF was ${EXTRA_OECONF}"
}

do_compile() {
	unset CC LD CCLD CXX RANLIB AR STRIP CFLAGS LDFLAGS CXXFLAGS CPPFLAGS
	install -d include/asm/	
	install -m 0644 ${WORKDIR}/sharp_char.h include/asm/
	install -d include/linux/
	install -m 0644 ${WORKDIR}/switches.h   include/linux/

	# Create symlinks first and then compile the library
	oe_runmake symlinks
	oe_runmake src-mt sub-src
}

do_stage() {
	rm -rf ${STAGING_DIR_HOST}/qt2
	install -d ${STAGING_DIR_HOST}/qt2/lib
	oe_libinstall -so -C lib lib${PN} ${STAGING_DIR_HOST}/qt2/lib
	rm -f include/qxt.h
	install -d ${STAGING_DIR_HOST}/qt2/include
	cp -pfLR include/* ${STAGING_DIR_HOST}/qt2/include
	cp -pPR lib/fonts ${STAGING_DIR_HOST}/qt2/lib/
}

do_install() {
	oe_libinstall -so -C lib lib${PN} ${D}${libdir}

	install -d ${D}/${includedir}/qte
	install -m 0644 include/*.h ${D}/${includedir}/qte/
}

FILES_${PN} = "${libdir}/lib${PN}.so.*"
FILES_${PN}-dev = "${includedir}/* ${libdir}/lib${PN}.so"
FILES_${PN}-dbg += "${libdir}/.debug/"
