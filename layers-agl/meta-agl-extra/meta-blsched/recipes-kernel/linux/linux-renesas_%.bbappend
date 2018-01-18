SRC_URI_append = " \
	${@bb.utils.contains('MACHINE_FEATURES','biglittle','file://procevent.cfg','',d)} \
	${@bb.utils.contains('MACHINE_FEATURES','biglittle','file://scheddebug.cfg','',d)} \
	"
