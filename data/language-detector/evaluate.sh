#!/usr/bin/env bash
set -euo pipefail

readonly OPENNLP="~/bin/apache-opennlp-1.9.0/bin/opennlp"
readonly MODEL="language_model.bin"

${OPENNLP} LanguageDetector ${MODEL}
