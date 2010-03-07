DESCRIPTION = "GNOME menus"
SECTION = "x11/gnome"
LICENSE = "GPL"

DEPENDS = "python gnome-vfs libxml2 gconf popt gtk+"

inherit gnome pkgconfig

PACKAGES += "${PN}-python"
FILES_${PN} += "${datadir}/desktop-directories/"
FILES_${PN}-python = "${libdir}/python*"
FILES_${PN}-dbg += "${libdir}/python*/site-packages/*/.debug \
                    ${libdir}/python*/site-packages/.debug"

