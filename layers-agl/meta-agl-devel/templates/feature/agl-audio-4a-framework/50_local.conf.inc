# In order to enable the audio-4a-framework features ,
# enables the following line:
#
DISTRO_FEATURES_append = " agl-audio-4a-framework "

# asound.conf is provided by the 4a-hal-config (and not by asound.state)
VIRTUAL-RUNTIME_alsa-state = "4a-hal-config"
# additionally there is a bug in alsa-utils-alsactl where it pulls in alsa-state directly
RRECOMMENDS_pn-alsa-utils-alsactl = "VIRTUAL-RUNTIME_alsa-state"

# why should a config package have -dev ... I'll leave that answer to you.
PACKAGES_pn-alsa-state_remove = "alsa-state-dev"
PACKAGES_pn-alsa-state_remove = "alsa-states-dev"
PACKAGES_pn-alsa-state = "alsa-state alsa-states"
