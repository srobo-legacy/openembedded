# Copyright Ulf Samuelsson <ulf.samuelsson@atmel.com> (c) 2007
# License: MIT (see http://www.opensource.org/licenses/mit-license.php
#               for a copy of the license)
#
# Filename: alsa-state_atmel.bb

DESCRIPTION = "Alsa Scenario Files"
LICENSE = "MIT"
PV = "atmel"
PR = "r0"

SRC_URI = "\
	file://asound.conf \
	file://asound.state \
	file://alsa-state \
	"

require alsa-state.inc

