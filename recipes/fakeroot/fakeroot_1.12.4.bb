DESCRIPTION = "Gives a fake root environment"
HOMEPAGE = "http://fakeroot.alioth.debian.org"
SECTION = "base"
LICENSE = "GPL"

SRC_URI = "\
  ${DEBIAN_MIRROR}/main/f/fakeroot/fakeroot_${PV}.tar.gz;name=fakeroot1124targz \
  file://configure-libtool.patch;patch=1 \
"
SRC_URI[fakeroot1124targz.md5sum] = "aaefede2405a40c87438e7e833d69b70"
SRC_URI[fakeroot1124targz.sha256sum] = "dbcab1f495b857e67feff882e018ca59958b8d189ff1f76684d28e35463ec29d"
	    
inherit autotools

do_stage() {
        install -d ${STAGING_INCDIR}/fakeroot
        install -m 644 *.h ${STAGING_INCDIR}/fakeroot
        autotools_stage_all
}

# fakeroot needs getopt which is provided by the util-linux package
RDEPENDS = "util-linux"

BBCLASSEXTEND="native"
