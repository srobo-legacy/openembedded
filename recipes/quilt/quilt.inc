DESCRIPTION = "Tool to work with series of patches."
HOMEPAGE = "http://savannah.nongnu.org/projects/quilt/"
SECTION = "devel"
LICENSE = "GPL"

SRC_URI = "http://download.savannah.gnu.org/releases/quilt/quilt-${PV}.tar.gz;name=quilt048targz \
           file://install.patch;patch=1"
SRC_URI[quilt048targz.md5sum] = "f77adda60039ffa753f3c584a286f12b"
SRC_URI[quilt048targz.sha256sum] = "73fd760d3b5cbf06417576591dc37d67380d189392db9000c21b7cbebee49ffc"

S = "${WORKDIR}/quilt-${PV}"
