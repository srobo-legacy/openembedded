DESCRIPTION = "GGZ Gaming Zone lib" 
LICENSE = "LGPLv2"
DEPENDS = "gnutls libgcrypt" 

SRC_URI = "http://ftp.belnet.be/packages/ggzgamingzone/ggz/${PV}/${PN}-${PV}.tar.gz"

inherit autotools

