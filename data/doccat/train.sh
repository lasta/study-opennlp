#!/usr/bin/env bash
set -euo pipefail

readonly OPENNLP="${HOME}/bin/apache-opennlp-1.9.0/bin/opennlp"
readonly MODEL="model.bin"
readonly LANG="ja"
readonly TRAINER="trainer.txt"
readonly ENCODE="UTF-8"

${OPENNLP} DoccatTrainer -model ${MODEL} -lang ${LANG} -data ${TRAINER} -encoding ${ENCODE}

