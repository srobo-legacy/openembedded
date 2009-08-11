require linux.inc

PR = "r0"

S = "${WORKDIR}/linux-${PV}"

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

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}.tar.bz2 \
	"

SRC_URI_append_at91sam9g10ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g20ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91sam9g45ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

#SRC_URI_append_at91sam9m10ek = \
#	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
#	"

SRC_URI_append_at91sam9rlek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

SRC_URI_append_at91sam9260ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

SRC_URI_append_at91sam9261ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

SRC_URI_append_at91sam9263ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	file://defconfig \
	"

SRC_URI_append_at91capadk = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

#SRC_URI_append_at91capstk = \
#	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
#	"

SRC_URI_append_at572d940hfek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"




