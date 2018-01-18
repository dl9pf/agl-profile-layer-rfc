import unittest
from oeqa.sdk.case import OESDKTestCase
from oeqa.sdk.utils.sdkbuildproject import SDKBuildProject


class BuildIptablesTest(OESDKTestCase):
    td_vars = ['DATETIME']

    @classmethod
    def setUpClass(self):
        dl_dir = self.td.get('DL_DIR', None)

        self.project = SDKBuildProject(self.tc.sdk_dir + "/iptables/", self.tc.sdk_env, 
                        "http://downloads.yoctoproject.org/mirror/sources/iptables-1.4.13.tar.bz2",
                        self.tc.sdk_dir, self.td['DATETIME'], dl_dir=dl_dir)
        self.project.download_archive()

        machine = self.td.get("MACHINE")

        if not self.tc.hasHostPackage("packagegroup-cross-canadian-%s" % machine):
            raise unittest.SkipTest("SDK doesn't contain a cross-canadian toolchain")

    def test_iptables(self):
        self.assertEqual(self.project.run_configure(), 0,
                        msg="Running configure failed")

        self.assertEqual(self.project.run_make(), 0,
                        msg="Running make failed")

        self.assertEqual(self.project.run_install(), 0,
                        msg="Running make install failed")

    @classmethod
    def tearDownClass(self):
        self.project.clean()
