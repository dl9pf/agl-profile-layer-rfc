#!/bin/bash

ZIP_1="R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-weston2-20170904.zip"
ZIP_2="R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-weston2-20170904.zip"

COPY_SCRIPT="$METADIR/meta-renesas-rcar-gen3/meta-rcar-gen3/docs/sample/copyscript/copy_evaproprietary_softwares.sh"

test -f ${XDG_CONFIG_HOME:-~/.config}/user-dirs.dirs && source ${XDG_CONFIG_HOME:-~/.config}/user-dirs.dirs
DOWNLOAD_DIR=${XDG_DOWNLOAD_DIR:-$HOME/Downloads}
EXTRACT_DIR=$METADIR/binary-tmp

stdout_in_terminal=1
[[ -t 1 ]] && stdout_in_terminal=1
function color {
	[[ $stdout_in_terminal == 0 ]] && return
	for k in $*; do
		case $k in
			bold) tput bold 2>/dev/null;;
			none) tput sgr0 2>/dev/null;;
			*) tput setaf $k 2>/dev/null;;
		esac
	done
}
color_green=$(color bold 2)
color_yellow=$(color bold 3)
color_red=$(color bold 1)
color_none=$(color none)

function error() {
	echo "${color_red}$@${color_none}" >&2
}

function log() {
	echo "$@" >&2
}

function copy_mm_packages() {
        if [ -f $DOWNLOAD_DIR/$ZIP_1 -a -f $DOWNLOAD_DIR/$ZIP_2 ]; then
                mkdir -p $EXTRACT_DIR
                cp --update $DOWNLOAD_DIR/$ZIP_1 $EXTRACT_DIR
                cp --update $DOWNLOAD_DIR/$ZIP_2 $EXTRACT_DIR
        else
                error "ERROR: FILES \""+$DOWNLOAD_DIR/$ZIP_1+"\" NOT EXTRACTING CORRECTLY"
                error "ERROR: FILES \""+$DOWNLOAD_DIR/$ZIP_2+"\" NOT EXTRACTING CORRECTLY"
                log   "The graphics and multimedia acceleration packages for "
                log   "the R-Car Gen3 board BSP 2.19 can be downloaded from:"
                log   " <https://www.renesas.com/en-us/solutions/automotive/rcar-demoboard-2.html>"
                log
                error  "These 2 files from there should be stored in your"
                error  "'$DOWNLOAD_DIR' directory."
                error  "  $ZIP_1"
                error  "  $ZIP_2"
                return 1
        fi

        if [ -f $COPY_SCRIPT ]; then
                cd $METADIR/meta-renesas-rcar-gen3/
                $COPY_SCRIPT -d -f $EXTRACT_DIR
                cd ..
        else
                log   "scripts to copy drivers for Gen3 not found."
                return 1
        fi
}
