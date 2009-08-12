require linux.inc

PR = "r0"

S = "${WORKDIR}/linux-2.6.30"

# Mark archs/machines that this kernel supports
DEFAULT_PREFERENCE = "-1"

DEFAULT_PREFERENCE_at91sam9g10ek = "2"
DEFAULT_PREFERENCE_at91sam9g20ek = "2"
DEFAULT_PREFERENCE_at91sam9g45ek = "2"
DEFAULT_PREFERENCE_at91sam9m10ek = "-1"
DEFAULT_PREFERENCE_at91sam9rlek = "2"
DEFAULT_PREFERENCE_at91sam9260ek = "2"
DEFAULT_PREFERENCE_at91sam9261ek = "2"
DEFAULT_PREFERENCE_at91sam9263ek = "2"
DEFAULT_PREFERENCE_at91capadk = "2"
DEFAULT_PREFERENCE_at91capstk = "-1"
DEFAULT_PREFERENCE_at572d940hfek = "2"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-2.6.30.tar.bz2 \
           ${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/patch-2.6.30.2.bz2;patch=1 \
	"


SRC_URI_append_at91sam9g10df = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g20dfc = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g45ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9xedfc = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9260dfc = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9261df = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9263dfc = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

# -------------------------
SRC_URI_append_at91sam9260df = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9260ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9260nf = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9261ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9263ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g10ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g10nf = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g20df = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g20ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g20nf = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"



#
# Boards which rely on kernel defconfigs are here.
# Not every board is supported in the kernel
#
require linux-2.6.30.2-at91-kernel-config.inc

