# Baylibre's AGL blsched layer

This README file contains information on the contents of the
meta-blsched layer.

Please see the corresponding sections below for details.

### Dependencies
-------------------------

The linux kernel must have the following config options enabled:
CONFIG_CONNECTOR
CONFIG_PROC_EVENTS
CONFIG_SCHED_DEBUG

### Patches
-----------

Please submit any patches against the meta-blsched layer to the
the maintainers:

* Michael Turquette <mturquette@baylibre.com>
* Frode Isaksen <fisaksen@baylibre.com>
* Jerome Brunet <jbrunet@baylibre.com>

## I. Description and provided packages:

The layer provides blsched utility. This package is a utility to improve scheduling efficiency on big.LITTLE architecture.

+ Provided packages :
	- blsched : see https://github.com/BayLibre/bLsched/blob/master/README.md
	- blsched-daemon : systemd service tailored for r8a7795 and r8a7796 SoCs

## II. Adding the meta-blsched layer to your AGL build

1. Download meta-agl-extra at `$AGL_TOP`
2. Add `blsched` to the feature of your AGL build<br>
```shell
source meta-agl/scripts/aglsetup.sh -m $MACHINE -b <your-other-features> blsched
```

With this `meta-blsched` will be added to your `conf/bblayers.conf`. `blsched` and `blsched-daemon` will be appended to your image.
