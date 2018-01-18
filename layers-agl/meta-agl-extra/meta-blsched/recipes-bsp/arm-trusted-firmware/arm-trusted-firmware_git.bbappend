export PSCI_DISABLE_BIGLITTLE_IN_CA57BOOT="${@bb.utils.contains('MACHINE_FEATURES','biglittle','0','1',d)}"
