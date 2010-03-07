HOMEPAGE = "http://www.gnu.org/software/gperf"
LICENSE  = "GPL"
SUMMARY  = "Generate a perfect hash function from a set of keywords"

SRC_URI  = "${GNU_MIRROR}/gperf/gperf-${PV}.tar.gz;name=gperf303targz \
            file://autoreconf.patch;patch=1"
SRC_URI[gperf303targz.md5sum] = "cc20e58975a38075440423c8fb85fd00"
SRC_URI[gperf303targz.sha256sum] = "63287527c8d9e27e801cf0804436f3494bd569db05d49dcdd2a942ae72fa9055"

inherit autotools

NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"
