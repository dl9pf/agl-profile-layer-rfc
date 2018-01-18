# This layer contains the WIP feature/demo code only
#---------------------------------------------------
BBLAYERS =+ " \
   ${METADIR}/layers-agl/meta-agl/meta-agl-upstream-extensions \
   ${METADIR}/layers-agl/meta-agl/meta-agl-profile-apis \
   ${METADIR}/layers-agl/meta-agl/meta-agl-profile-weston \
   ${METADIR}/layers-agl/meta-agl/meta-agl-profile-weston-ivi \
   ${METADIR}/layers-agl/meta-agl-demo \
"

# These are the dependencies of the AGL DEMO Layer
#-------------------------------------------------
BBLAYERS =+ " \
   ${METADIR}/layers-upstream/meta-qt5 \
"

