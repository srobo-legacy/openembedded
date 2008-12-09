DEPENDS =+ "gettext-native virtual/libiconv virtual/libintl"
EXTRA_OECONF += "--enable-nls"

def gettext_after_parse(d):
    import bb
    depends = bb.data.getVar('DEPENDS', d, 1) or ""
    # Remove the NLS bits if USE_NLS is no.
    if bb.data.getVar('USE_NLS', d, 1) == 'no':
        cfg = oe_filter_out('^--(dis|en)able-nls$', bb.data.getVar('EXTRA_OECONF', d, 1) or "", d)
        cfg += " --disable-nls"
        bb.data.setVar('EXTRA_OECONF', cfg, d)
        depends = oe_filter_out('^(gettext-native|virtual/libiconv|virtual/libintl)$', depends, d)
    # cross and native packages must be handled diffrently
    # gettext-native should be enough as dependency
    else:
	if bb.data.getVar('PN', d, 1).find('-cross') >=0 or bb.data.getVar('PN', d, 1).find('-native') >= 0:
		depends = oe_filter_out('^(virtual/libiconv|virtual/libintl)$', depends, d)
    bb.data.setVar('DEPENDS', depends, d)

python () {
    gettext_after_parse(d)
}

