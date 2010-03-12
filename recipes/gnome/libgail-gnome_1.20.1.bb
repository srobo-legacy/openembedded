DESCRIPTION="Gnome Accessibility library"
LICENSE="GPLv2"

PR ="r0"

DEPENDS="atk gtk+ libbonobo libbonoboui gnome-panel at-spi"

inherit gnome

FILES_${PN} += "${libdir}/gtk-2.0/modules/*.so"
FILES_${PN}-dbg += "${libdir}/gtk-2.0/modules/.debug"

