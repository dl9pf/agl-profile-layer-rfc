SUMMARY = "Mongoose - Embedded Web Server / Embedded Networking Library"
DESCRIPTION = "Mongoose is ideal for embedded environments. It has been designed for connecting devices and bringing them online.  \
               Mongoose makes embedded network programming fast, robust, and easy."

HOMEPAGE = "https://www.cesanta.com/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3548d4c6b2c754880739734d5e100aa9"

SECTION = "libs"

PV = "6.6+git${SRCPV}"
SRCREV = "a23daa1547937b17bc30894d4bf19ddca80c7d35"
SRC_URI = "git://github.com/cesanta/mongoose.git;branch=master"

S = "${WORKDIR}/git"

DEPENDS = "openssl"

MONGOOSE_EXLIBS ?= "libmongoose.so libmongoose_ssl.so libmongoose_mqtt.so"

python () {
    d.setVar("CFLAGS_DEF", "-DMG_ENABLE_THREADS -DMG_ENABLE_COAP -DMG_ENABLE_HTTP_STREAMING_MULTIPART")
    d.setVar("CFLAGS_SSL", "-DMG_ENABLE_THREADS -DMG_ENABLE_SSL -lssl -lcrypto")
    d.setVar("CFLAGS_MQTT", "-DMG_ENABLE_THREADS -DMG_ENABLE_SSL -lssl -lcrypto -DMG_ENABLE_MQTT_BROKER -DMG_ENABLE_HTTP=0")
}

FILES_${PN} += "${exec_prefix}/lib/libmongoose.so \
                ${exec_prefix}/lib/libmongoose_mqtt.so \
                ${exec_prefix}/lib/libmongoose_ssl.so \
               "
               
FILES_${PN}-dbg = "${exec_prefix}/src/*"

FILES_${PN}-dev = "${exec_prefix}/include/mongoose.h"

FILES_${PN}-doc = "${exec_prefix}/share/doc/*"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {

    if [ "${@bb.utils.contains('MONGOOSE_EXLIBS', 'libmongoose.so', 'def', '', d)}" = "def" ]; then
        ${CC} mongoose.c -fPIC -shared -o libmongoose.so ${CFLAGS_DEF}
    fi
        
    if [ "${@bb.utils.contains('MONGOOSE_EXLIBS', 'libmongoose_ssl.so', 'ssl', '', d)}" = "ssl" ]; then
        ${CC} mongoose.c -fPIC -shared -o libmongoose_ssl.so ${CFLAGS_SSL}
    fi
 
    if [ "${@bb.utils.contains('MONGOOSE_EXLIBS', 'libmongoose_mqtt.so', 'mqtt', '', d)}" = "mqtt" ]; then
        ${CC} mongoose.c -fPIC -shared -o libmongoose_mqtt.so ${CFLAGS_MQTT}
    fi
}

do_install() {

    install -d ${D}${exec_prefix}/lib/
    install -d ${D}${exec_prefix}/include/
    install -d ${D}${exec_prefix}/src/debug/mongoose/6.6/
    install -d ${D}${exec_prefix}/share/doc/
    
    for libs in ${MONGOOSE_EXLIBS}
        do
            install -m 0644 ${S}/${libs} ${D}${exec_prefix}/lib/${libs}
        done

    install -m 0644 ${S}/mongoose.h ${D}${exec_prefix}/include/mongoose.h
    install -m 0644 ${S}/mongoose.c ${D}${exec_prefix}/src/debug/mongoose/6.6/mongoose.c
    install -m 0644 ${S}/mongoose.h ${D}${exec_prefix}/src/debug/mongoose/6.6/mongoose.h
    
    tar -czf docs.tar.gz ${S}/docs/
    install -m 0644 ${S}/docs.tar.gz ${D}${exec_prefix}/share/doc/docs.tar.gz
    install -m 0644 ${S}/LICENSE ${D}${exec_prefix}/share/doc/LICENSE
    install -m 0644 ${S}/CONTRIBUTING.md ${D}${exec_prefix}/share/doc/CONTRIBUTING.md
}
