# Copyright (C) 2016 Aleksi Suomalainen <aleksi.suomalainen@protonmail.com>
# Released under the GPLv2 license (see LICENSE for the terms)

require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "N950 Kernel with patches from Philip Matievic"
HOMEPAGE = "https://github.com/nemomobile/kernel-adaptation-n950-n9"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "n950"

SRC_URI = "git://github.com/nemomobile/kernel-adaptation-n950-n9.git;branch=master;protocol=https \
    file://defconfig"
SRCREV = "50f16863101beb727602f3c41bcb08c00062a1f4"
LINUX_VERSION ?= "3.5.3"
PV = "${LINUX_VERSION}+n950"
S = "${WORKDIR}/git"
B = "${S}"


# Removes some headers that are installed incorrectly

do_configure_prepend() {
    # Fixes build with GCC5
    echo "#include <linux/compiler-gcc4.h>" > ${S}/include/linux/compiler-gcc5.h
}

do_install_append() {
    rm -rf ${D}/usr/src/usr/
}

