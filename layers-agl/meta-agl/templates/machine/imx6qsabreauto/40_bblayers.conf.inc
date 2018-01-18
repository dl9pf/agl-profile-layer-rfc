# This must be parsed after qt5, etc. layers so that they are correctly
# recognized by meta-freescale/dynamic-layers
BBLAYERS =+ "\
  ${METADIR}/layers-upstream/meta-freescale \
  ${METADIR}/layers-upstream/meta-freescale-3rdparty \
  "
#  ${METADIR}/layers-upstream/meta-freescale-distro \
#
