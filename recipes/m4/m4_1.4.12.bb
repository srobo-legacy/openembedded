DESCRIPTION = "GNU m4 is an implementation of the traditional Unix macro \
processor."
LICENSE = "GPLv3"
SRC_URI = "${GNU_MIRROR}/m4/m4-${PV}.tar.gz;name=m41412targz"
SRC_URI[m41412targz.md5sum] = "0499a958a561781b125c740f18061ea8"
SRC_URI[m41412targz.sha256sum] = "47e8f9a33ba06fa6710b42d6f6ded41f45027f6f4039b0a3ed887c5116bc2173"
S = "${WORKDIR}/m4-${PV}"

inherit autotools

EXTRA_OEMAKE += "'infodir=${infodir}'"

do_configure() {
	oe_runconf
}
