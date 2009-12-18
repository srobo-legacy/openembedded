# Cross packages are built indirectly via dependency,
# no need for them to be a direct target of 'world'
EXCLUDE_FROM_WORLD = "1"

# Save PACKAGE_ARCH before changing HOST_ARCH
OLD_PACKAGE_ARCH := "${PACKAGE_ARCH}"
PACKAGE_ARCH = "${OLD_PACKAGE_ARCH}"
# Also save BASE_PACKAGE_ARCH since HOST_ARCH can influence it
OLD_BASE_PACKAGE_ARCH := "${BASE_PACKAGE_ARCH}"
BASE_PACKAGE_ARCH = "${OLD_BASE_PACKAGE_ARCH}"

PACKAGES = ""

HOST_ARCH = "${BUILD_ARCH}"
HOST_VENDOR = "${BUILD_VENDOR}"
HOST_OS = "${BUILD_OS}"
HOST_PREFIX = "${BUILD_PREFIX}"
HOST_CC_ARCH = "${BUILD_CC_ARCH}"
HOST_EXEEXT = "${BUILD_EXEEXT}"
BASEPKG_HOST_SYS = "${HOST_ARCH}${HOST_VENDOR}-${HOST_OS}"

CPPFLAGS = "${BUILD_CPPFLAGS}"
CFLAGS = "${BUILD_CFLAGS}"
CXXFLAGS = "${BUILD_CFLAGS}"
LDFLAGS = "${BUILD_LDFLAGS}"
LDFLAGS_build-darwin = "-L${STAGING_LIBDIR_NATIVE}"

TOOLCHAIN_OPTIONS = ""

# Path mangling needed by the cross packaging
# Note that we use := here to ensure that libdir and includedir are
# target paths, not CROSS_DIR paths.
target_libdir := "${libdir}"
target_includedir := "${includedir}"
target_base_libdir := "${base_libdir}"
target_prefix := "${prefix}"

# Overrides for paths
prefix = "${CROSS_DIR}"
base_prefix = "${prefix}"
exec_prefix = "${prefix}"
base_sbindir = "${base_prefix}/bin"
sbindir = "${exec_prefix}/bin"

do_install () {
	oe_runmake 'DESTDIR=${D}' install
}

do_stage () {
	autotools_stage_all
}

#
# Override the default sysroot staging copy since this won't look like a target system
#
sysroot_stage_all() {
	sysroot_stage_dir ${D} ${SYSROOT_DESTDIR}
	install -d ${SYSROOT_DESTDIR}${STAGING_DIR_TARGET}${target_base_libdir}/
	install -d ${SYSROOT_DESTDIR}${STAGING_DIR_TARGET}${target_libdir}/  
	mv ${SYSROOT_DESTDIR}${target_base_libdir}/* ${SYSROOT_DESTDIR}${STAGING_DIR_TARGET}${target_base_libdir}/ || true
	mv ${SYSROOT_DESTDIR}${target_libdir}/* ${SYSROOT_DESTDIR}${STAGING_DIR_TARGET}${target_libdir}/ || true
}
