SUMMARY ="enOcean recipe for yocto"

DESCRIPTION = "enOcean allow to control device that use this protocole for home automation ..."

AUTHOR = "modjo.buggy22@orange.fr"
 
DEPENDS = "\
    autoconf  \
    automake  \
"

LICENSE_FLAGS = "trial_enocean"

LICENSE = "CLOSED"

LIC_FILES_CHKSUM = " \
    file://${WORKDIR}/EOLink/LICENSE;md5=bad3069f1239057481be546550adfeca \
    file://${WORKDIR}/EOLink/LICENSE_TRIAL;md5=f812f2a3f8d3e12164316914e903e622 \
"

SRC_URI = "file://EnOceanLinkTrial_1_9_0_0.zip \
          file://0001-change-default-var-pi-script.patch \
          file://0002-change-path-includes-files.patch \
"

S = "${WORKDIR}/EOLink"

PACKAGES = "${PN} ${PN}-staticdev ${PN}-dev"

do_configure() {
   chmod 777 ./*.sh
   chmod 777 ./configure
   aclocal
   automake
}

do_compile() {
    ./pi.sh
}

do_install () {
    install -d ${D}${libdir}
    install -m 0644 libEOLink.a ${D}${libdir}
    install -m 0644 libEOLink.la ${D}${libdir}
    install -d ${D}${includedir}/${PN}/api
    install -m 0644 Includes/api/* ${D}${includedir}/${PN}/api
    install -d ${D}${includedir}/${PN}/Profiles
    install -m 0644 Profiles/*.h ${D}${includedir}/${PN}/Profiles
    install -m 0644 Includes/*.h ${D}${includedir}/${PN}
    install -m 0644 eoLink.h ${D}${includedir}
}

FILES_${PN} += " \
                ${libdir}/lib*.la \
"
