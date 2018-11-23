package com.lasta.data

import com.orangesignal.csv.Csv
import com.orangesignal.csv.CsvConfig
import com.orangesignal.csv.handlers.CsvEntityListHandler
import java.io.File

class CacheLoader<T> {
    companion object {
        private const val ENCODE: String = "UTF-8"
        private val CONFIG: CsvConfig = CsvConfig().apply {
            separator = '\t'
            isQuoteDisabled = true
            isIgnoreLeadingWhitespaces = true
            isIgnoreTrailingWhitespaces = true
            isIgnoreEmptyLines = true
            escape = '\\'
        }
    }

    fun load(file: File, entityClass: Class<T>, encode: String = ENCODE, config: CsvConfig = CONFIG): List<T> =
        Csv.load(file, encode, config, CsvEntityListHandler(entityClass))

}