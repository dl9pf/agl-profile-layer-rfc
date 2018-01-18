pkg_postinst_${PN} () {
   # Fail on error.
   set -e

   # It would be nice to run the code below while building an image,
   # but currently the calls to cynara-db-chsgen (a binary) in
   # cynara-db-migration (a script) prevent that. Rely instead
   # on OE's support for running failed postinst scripts at first boot.
   if [ x"$D" != "x" ]; then
      exit 1
   fi

   mkdir -p $D${sysconfdir}/cynara
   ${CHSMACK} -a System $D${sysconfdir}/cynara

   # Strip git patch level information, the version comparison code
   # in cynara-db-migration only expect major.minor.patch version numbers.
   VERSION=${@d.getVar('PV',d,1).split('+git')[0]}
   if [ -d $D${localstatedir}/cynara ] ; then
      # upgrade
      echo "NOTE: updating cynara DB to version $VERSION"
      $D${sbindir}/cynara-db-migration upgrade -f 0.0.0 -t $VERSION
   else
      # install
      echo "NOTE: creating cynara DB for version $VERSION"
      mkdir -p $D${localstatedir}/cynara
      ${CHSMACK} -a System $D${localstatedir}/cynara
      $D${sbindir}/cynara-db-migration install -t $VERSION
   fi

   # Workaround for systemd.bbclass issue: it would call
   # "systemctl start" without "--no-block", but because
   # the service is not ready to run at the time when
   # this scripts gets executed by run-postinsts.service,
   # booting deadlocks.
   echo "NOTE: enabling and starting cynara service"
   systemctl enable cynara
   systemctl start --no-block cynara
}
