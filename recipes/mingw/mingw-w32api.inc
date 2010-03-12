inherit canadian-native autotools

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "virtual/${TARGET_PREFIX}gcc-initial"

PROVIDES = "mingw-w32api"

PACKAGES = ""

B = "${WORKDIR}/build-${TARGET_SYS}"
