FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#-------------------------------------------------------------------------
# Fix for CVE-2017-1000364 by backporting the upstream patches.
SRC_URI_append = "\
       file://0001-mm-larger-stack-guard-gap-between-vmas.patch \
       file://0002-Allow-stack-to-grow-up-to-address-space-limit.patch \
       file://0003-mm-fix-new-crash-in-unmapped_area_topdown.patch \
       "
