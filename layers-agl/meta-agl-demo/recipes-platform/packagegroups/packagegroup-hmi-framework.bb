SUMMARY = "The software for the AGL hmi framework 2017"
DESCRIPTION = "A set of packages belong to the hmi framework 2017"

LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} += " \
  agl-service-windowmanager-2017 \
  agl-service-homescreen-2017 \
  homescreen-2017 \
  libwindowmanager \
  qlibwindowmanager \
  libhomescreen-2017 \
  runxdg \
"
