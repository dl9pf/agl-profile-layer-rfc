SUMMARY = "WebKit web rendering engine for the GTK+ platform"
HOMEPAGE = "http://www.webkitgtk.org/"
BUGTRACKER = "http://bugs.webkit.org/"

LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://Source/JavaScriptCore/COPYING.LIB;md5=d0c6d6397a5d84286dda758da57bd691 \
                    file://Source/WebCore/LICENSE-APPLE;md5=4646f90082c40bcf298c285f8bab0b12 \
		    file://Source/WebCore/LICENSE-LGPL-2;md5=36357ffde2b64ae177b2494445b79d21 \
		    file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80 \
		   "

SRC_URI = "http://www.webkitgtk.org/releases/${BPN}-${PV}.tar.xz \
           file://0001-FindGObjectIntrospection.cmake-prefix-variables-obta.patch \
           file://0001-When-building-introspection-files-add-CMAKE_C_FLAGS-.patch \
           file://0001-OptionsGTK.cmake-drop-the-hardcoded-introspection-gt.patch \
           file://musl-fixes.patch \
           file://ppc-musl-fix.patch \
           file://0001-Fix-racy-parallel-build-of-WebKit2-4.0.gir.patch \
           file://0001-Tweak-gtkdoc-settings-so-that-gtkdoc-generation-work.patch \
           file://0001-WebKitMacros-Append-to-I-and-not-to-isystem.patch \
           file://detect_atomics.patch \
           file://x32_support.patch \
           file://cross-compile.patch \
           "

SRC_URI[md5sum] = "7fe3cb2699e64f969b285823c5ae2516"
SRC_URI[sha256sum] = "3ca8f1c33a9b43d6c753dcac1c0788656930e06382b10fdf5c2805ea8f96369f"

inherit cmake pkgconfig gobject-introspection perlnative distro_features_check upstream-version-is-even gtk-doc

# We cannot inherit pythonnative because that would conflict with inheriting python3native
# (which is done by gobject-introspection). But webkit only needs the path to native Python 2.x binary
# so we simply set it explicitly here.
EXTRANATIVEPATH += "python-native"

# depends on libxt
REQUIRED_DISTRO_FEATURES = "x11"

DEPENDS = "zlib libsoup-2.4 curl libxml2 cairo libxslt libxt libidn gnutls \
           gtk+3 gstreamer1.0 gstreamer1.0-plugins-base flex-native gperf-native sqlite3 \
	   pango icu bison-native gawk intltool-native libwebp \
	   atk udev harfbuzz jpeg libpng pulseaudio librsvg libtheora libvorbis libxcomposite libxtst \
	   ruby-native libnotify gstreamer1.0-plugins-bad \
	   python-native \
          "

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', 'wayland' ,d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'webgl opengl', '' ,d)} \
                   enchant \
                   libsecret \
                  "

PACKAGECONFIG[wayland] = "-DENABLE_WAYLAND_TARGET=ON,-DENABLE_WAYLAND_TARGET=OFF,wayland"
PACKAGECONFIG[x11] = "-DENABLE_X11_TARGET=ON,-DENABLE_X11_TARGET=OFF,virtual/libx11"
PACKAGECONFIG[geoclue] = "-DENABLE_GEOLOCATION=ON,-DENABLE_GEOLOCATION=OFF,geoclue"
PACKAGECONFIG[enchant] = "-DENABLE_SPELLCHECK=ON,-DENABLE_SPELLCHECK=OFF,enchant"
PACKAGECONFIG[gtk2] = "-DENABLE_PLUGIN_PROCESS_GTK2=ON,-DENABLE_PLUGIN_PROCESS_GTK2=OFF,gtk+"
PACKAGECONFIG[gles2] = "-DENABLE_GLES2=ON,-DENABLE_GLES2=OFF,virtual/libgles2"
PACKAGECONFIG[webgl] = "-DENABLE_WEBGL=ON,-DENABLE_WEBGL=OFF,virtual/libgl"
PACKAGECONFIG[opengl] = "-DENABLE_OPENGL=ON,-DENABLE_OPENGL=OFF,virtual/libgl"
PACKAGECONFIG[libsecret] = "-DENABLE_CREDENTIAL_STORAGE=ON,-DENABLE_CREDENTIAL_STORAGE=OFF,libsecret"
PACKAGECONFIG[libhyphen] = "-DUSE_LIBHYPHEN=ON,-DUSE_LIBHYPHEN=OFF,libhyphen"

EXTRA_OECMAKE = " \
		-DPORT=GTK \
		-DCMAKE_BUILD_TYPE=Release \
		${@bb.utils.contains('GI_DATA_ENABLED', 'True', '-DENABLE_INTROSPECTION=ON', '-DENABLE_INTROSPECTION=OFF', d)} \
		${@bb.utils.contains('GTKDOC_ENABLED', 'True', '-DENABLE_GTKDOC=ON', '-DENABLE_GTKDOC=OFF', d)} \
		-DENABLE_MINIBROWSER=ON \
		"

# Javascript JIT is not supported on powerpc
EXTRA_OECMAKE_append_powerpc = " -DENABLE_JIT=OFF "
EXTRA_OECMAKE_append_powerpc64 = " -DENABLE_JIT=OFF "

# ARM JIT code does not build on ARMv4/5/6 anymore
EXTRA_OECMAKE_append_armv5 = " -DENABLE_JIT=OFF "
EXTRA_OECMAKE_append_armv6 = " -DENABLE_JIT=OFF "
EXTRA_OECMAKE_append_armv4 = " -DENABLE_JIT=OFF "

# binutils 2.25.1 has a bug on aarch64:
# https://sourceware.org/bugzilla/show_bug.cgi?id=18430
EXTRA_OECMAKE_append_aarch64 = " -DUSE_LD_GOLD=OFF "
EXTRA_OECMAKE_append_mipsarch = " -DUSE_LD_GOLD=OFF "
EXTRA_OECMAKE_append_toolchain-clang = " -DUSE_LD_GOLD=OFF "

EXTRA_OECMAKE_append_aarch64 = " -DWTF_CPU_ARM64_CORTEXA53=ON"

# JIT not supported on MIPS either
EXTRA_OECMAKE_append_mipsarch = " -DENABLE_JIT=OFF "

# JIT not supported on X32
# An attempt was made to upstream JIT support for x32 in
# https://bugs.webkit.org/show_bug.cgi?id=100450, but this was closed as
# unresolved due to limited X32 adoption.
EXTRA_OECMAKE_append_linux-gnux32 = " -DENABLE_JIT=OFF"

SECURITY_CFLAGS_remove_aarch64 = "-fpie"
SECURITY_CFLAGS_append_aarch64 = " -fPIE"

FILES_${PN} += "${libdir}/webkit2gtk-4.0/injected-bundle/libwebkit2gtkinjectedbundle.so"

RRECOMMENDS_${PN} += "ca-certificates"

# http://errors.yoctoproject.org/Errors/Details/20370/
ARM_INSTRUCTION_SET_armv4 = "arm"
ARM_INSTRUCTION_SET_armv5 = "arm"
ARM_INSTRUCTION_SET_armv6 = "arm"

# https://bugzilla.yoctoproject.org/show_bug.cgi?id=9474
# https://bugs.webkit.org/show_bug.cgi?id=159880
# JSC JIT can build on ARMv7 with -marm, but doesn't work on runtime.
# Upstream only tests regularly the JSC JIT on ARMv7 with Thumb2 (-mthumb).
ARM_INSTRUCTION_SET_armv7a = "thumb"
ARM_INSTRUCTION_SET_armv7r = "thumb"
ARM_INSTRUCTION_SET_armv7ve = "thumb"
