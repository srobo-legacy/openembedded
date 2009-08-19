#Angstrom X11 image, with apps and kernel modules included

ANGSTROM_EXTRA_INSTALL += " \
			  " 
XSERVER ?= "xserver-kdrive"
SPLASH ?= ' ${@base_contains("MACHINE_FEATURES", "screen", "psplash-angstrom", "",d)}'

export IMAGE_BASENAME = "x11-gpe-image"

DEPENDS = "task-base"
IMAGE_INSTALL = "\
    ${XSERVER} \
	task-base-extended \
	angstrom-x11-base-depends \
	angstrom-gpe-task-base \
	angstrom-gpe-task-settings \
	kernel-modules \
	hal \
	angstrom-gpe-task-pim \
	minicom \
	samba \
	vsftpd \
	ncftp \
	micro-emacs \
	u-boot-utils \
	perl \
	nano \
	bzip2 \
	mpg321 \
	madplay \
	mplayer \
	libmad \
	libid3tag \
	id3lib \
	ffmpeg \
	fbv \
	imagemagick \
	atmel-fixes \
	${SPLASH} \
	${ANGSTROM_EXTRA_INSTALL}"
#	gftp \
# 	gcalctool \
# 	gstreamer \
#	emacs \
# 	mpd FAILS in RUNTIME \
#	i2c FAILS \
#	i2c-tools might FAIL\
#	atmel-fixes \

RDEPENDS_task-base-alsa += "\
	alsa-utils-amixer"

IMAGE_PREPROCESS_COMMAND = "create_etc_timestamp"

#zap root password for release images
ROOTFS_POSTPROCESS_COMMAND += '${@base_conditional("DISTRO_TYPE", "release", "zap_root_password; ", "",d)}'

inherit image
