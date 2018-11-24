#!/usr/bin/env bash
set -euo pipefail

readonly OPENNLP="~/bin/apache-opennlp-1.9.0/bin/opennlp"
readonly MODEL="language_model.bin"
readonly TRAINER="trainer.txt"
readonly ENCODE="UTF-8"

${OPENNLP} LanguageDetectorTrainer -model ${MODEL} -data ${TRAINER} -encoding UTF-8
