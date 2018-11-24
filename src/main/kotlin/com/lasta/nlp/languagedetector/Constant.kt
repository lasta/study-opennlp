package com.lasta.nlp.languagedetector

object Constant {
    /**
     * ISO639_1to3-1 (2-letter language code) to ISO639_1to3-3 (3-latter language code)
     */
    val ISO639_1to3: Map<String, String> = mapOf(
        /**
         * English
         */
        "en" to "eng",
        /**
         * Japanese
         */
        "ja" to "jpn",
        /**
         * Mandarin Chinese
         * The dialect of Chinese spoken in Beijing and adopted as the official language for all of China.
         */
        "zh-CN" to "cmn",
        /**
         * Min Nan Chinese "nan"
         */
        "zh-TW" to "nan",
        /**
         * Korean
         */
        "ko" to "kor",
        /**
         * Unknown language.
         */
        "" to "und"
    )

    /**
     * ISO639_1to3-3 (3-latter language code) to ISO639_1to3-1 (2-letter language code)
     */
    val ISO639_3to1: Map<String, String> = ISO639_1to3.entries.associate { (k, v) -> v to k }
}