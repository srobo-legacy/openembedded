# Copyright Matthias Hentges <devel@hentges.net> (c) 2007
# License: MIT (see http://www.opensource.org/licenses/mit-license.php
#               for a copy of the license)
#
# Filename: alsa-state.bb

DESCRIPTION = "Alsa Scenario Files"
LICENSE = "MIT"
PV = "0.2.0"
PR = "r9"

SRC_URI = "\
  file://asound.conf \
  file://asound.state \
  file://alsa-state \
"

SRC_URI_append_a780 = "file://gsmhandset.state \
                       file://gsmheadset.state \
		       file://stereoout.state"

require alsa-state.inc
