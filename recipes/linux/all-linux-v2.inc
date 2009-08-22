require	linux.inc
CPU_FAMILY ?= "dummy-arch"
KERNEL_MAJOR  ?= "${PV}"
#${FILE_DIRNAME}/
KERNEL_MAJOR_DIR = "linux-${KERNEL_MAJOR}"
require ${KERNEL_MAJOR_DIR}/version.inc

KERNEL_VERSION_DIR = ${KERNEL_MAJOR_DIR}/${KERNEL_VERSION}
KERNEL_CPU_FAMILY_DIR=${KERNEL_MAJOR_DIR}/${CPU_FAMILY}

S = "${WORKDIR}/linux-${KERNEL_MAJOR}"

KERNEL_SOURCE_DIR="${KERNELORG_MIRROR}/pub/linux/kernel/v2.6"
SRC_URI = "${KERNEL_SOURCE_DIR}/linux-${KERNEL_MAJOR}.tar.bz2 "
MINOR_PATCH=${KERNEL_SOURCE_DIR}/patch-${KERNEL_MAJOR}.${KERNEL_MINOR}.bz2

# Include ${MINOR_PATCH} and any other common patches for this minor version
require	${KERNEL_VERSION_DIR}/patches/SRC_URI_append.inc
# Any customizations for this minor version
require	${KERNEL_VERSION_DIR}/linux.inc

# Customize for the CPU_FAMILY
# Note that this can contain further includes for minor versions
require	${KERNEL_CPU_FAMILY_DIR}/${CPU_FAMILY}.inc
require	${KERNEL_CPU_FAMILY_DIR}/${KERNEL_VERSION}/${KERNEL_VERSION}.inc
require	${KERNEL_CPU_FAMILY_DIR}/${KERNEL_VERSION}/patch-sets/SRC_URI_append.inc

