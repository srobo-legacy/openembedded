require poppler.inc


EXTRA_OECONF_append = " --disable-abiword-output "

RDEPENDS = "poppler-data"
