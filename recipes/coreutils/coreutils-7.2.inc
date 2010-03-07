require coreutils.inc

SRC_URI = "ftp://ftp.gnu.org/gnu/coreutils/coreutils-${PV}.tar.gz;name=coreutils72targz \
           file://coreutils-7.2-fix-sort-segfault.patch;patch=1"
SRC_URI[coreutils72targz.md5sum] = "427c2914d3eab956f317c9ec6a45e62a"
SRC_URI[coreutils72targz.sha256sum] = "dd77bfec92e5a3ad48abd8a5bda3f8d40149c4e24744e4173abc3cc6a731fdb2"
