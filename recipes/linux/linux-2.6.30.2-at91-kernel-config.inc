#
# Boards which rely on kernel defconfigs are here.
#
#

# ----------------------------------------------
# Supported in kernel
SRC_URI_append_at91cap9adk = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

# Not supported in kernel - uses "at91cap9adk_defconfig"
SRC_URI_append_at91cap9stk = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

# Not supported in kernel - broken
SRC_URI_append_at91sam9m10ek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

# Supported in kernel
SRC_URI_append_at91sam9rlek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"

# Supported in kernel
SRC_URI_append_at572d940hfek = \
	"ftp://www.at91.com/pub/buildroot/linux-2.6.30.2-at91-001.patch.gz;patch=1 \
	"



