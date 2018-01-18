# Local derivations during development, just add a layer in the folder meta-localdev
BBLAYERS =+ "${@'${METADIR}/layers-local/meta-localdev' if os.path.exists('${METADIR}/layers-local/meta-localdev') else ''}"
BBLAYERS =+ "${@'${METADIR}/layers-local/meta-project' if os.path.exists('${METADIR}/layers-local/meta-project') else ''}"

