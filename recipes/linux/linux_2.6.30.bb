require linux.inc

PR = "r6"

S = "${WORKDIR}/linux-${PV}"

# Mark archs/machines that this kernel supports
DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_mpc8315e-rdb = "1"
DEFAULT_PREFERENCE_calamari = "1"
DEFAULT_PREFERENCE_mx27ads = "1"
DEFAULT_PREFERENCE_kixrp435 = "1"
DEFAULT_PREFERENCE_at91sam9g45ekes	= "2"
DEFAULT_PREFERENCE_at91sam9g45ek	= "2"
DEFAULT_PREFERENCE_at91sam9m10ekes	= "2"
DEFAULT_PREFERENCE_at91sam9m10g45ek	= "2"
DEFAULT_PREFERENCE_at91sam9g10ek	= "2"
DEFAULT_PREFERENCE_at91sam9g20ek	= "2"

# machine boots with it, works but was not tested too much
DEFAULT_PREFERENCE_at91sam9263ek = "-1"
DEFAULT_PREFERENCE_tosa = "-1"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}.tar.bz2 \
           ${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/patch-${PV}.10.bz2;patch=1 \
           http://maxim.org.za/AT91RM9200/2.6/2.6.30-at91.patch.gz;patch=1 \
           file://aufs2-30.patch;patch=1 \
           file://defconfig"

SRC_URI_append_mpc8315e-rdb = " file://mpc8315erdb-add-msi-to-dts.patch;patch=1"

SRC_URI_append_at91sam9263ek = " file://hrw-linux-2.6.30-exp.patch;patch=1 "

SRC_URI_at91 = " \
	${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}.tar.bz2 \
	http://maxim.org.za/AT91RM9200/2.6/2.6.30-at91.patch.gz;patch=1 \
	ftp://www.at91.com/pub/buildroot/2.6.30-exp.2.patch.bz2;patch=1 \
	file://at91/linux-2.6.30-001-configurable-partition-size.patch.patch;patch=1 \
	file://at91/linux-2.6.30-002-mach-at91-Kconfig.patch;patch=1 \
	file://at91/linux-2.6.30-003-sam9m10g45ek.patch;patch=1 \
	file://defconfig"
