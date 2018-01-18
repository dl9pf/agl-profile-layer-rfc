SUMMARY     = "4A - High Level Audio API Service"
DESCRIPTION = "High Level Audio API service used in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-audio-4a/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/agl-service-audio-4a;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

DEPENDS += "alsa-lib json-c systemd af-binder glib-2.0"

do_aglwgt_deploy_append() {
	cat <<'EOF' >${D}/${sysconfdir}/agl-postinsts/99_4A_service_patch.sh
N=600
svcfile="/usr/local/lib/systemd/*/afm-service-agl-service-audio-4a*.service"
set -x
echo "-- TMP 4A INSTALL FIX from meta-agl/meta-app-framework/recipes-multimedia/agl-service-audio-4a/agl-service-audio-4a_git.bb - MUST BE REMOVED !!!"
while ! ls $svcfile > /dev/null; do
	if [ $N = 0 ]; then echo "-- TMP 4A INSTALL NOT FIXED"; exit 0; fi
	echo .
	sleep 0.2
	N=$(expr $N - 1)
done
sed -i '/\[Unit\]/ a Before=pulseaudio.service' $svcfile;
sed -i '/\[Unit\]/ a ConditionPathExistsGlob=/dev/snd/control*' $svcfile;

sed -i -e 's|/usr/bin/afb-daemon\>|& --ldpath=/usr/libexec/agl/afb-aaaa/lib:/usr/libexec/agl/4a-alsa-core/lib|' $svcfile
echo "-- TMP 4A INSTALL FIX END"

EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/99_4A_service_patch.sh
}
