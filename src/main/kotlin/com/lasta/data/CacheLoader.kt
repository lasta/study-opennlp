package com.lasta.data

import com.orangesignal.csv.Csv
import com.orangesignal.csv.CsvConfig
import com.orangesignal.csv.handlers.CsvEntityListHandler
import java.io.File
import java.io.IOException
import java.nio.file.Path

class CacheLoader<T> {
    companion object {
        private const val ENCODE: String = "UTF-8"
        private val CONFIG: CsvConfig = CsvConfig().apply {
            separator = '\t'
            isQuoteDisabled = false
            isEscapeDisabled = false
            breakString = "\n"
            nullString = ""
            isIgnoreEmptyLines = true
            lineSeparator = "\n"
        }
    }

    fun load(path: String, entityClass: Class<T>, encode: String = ENCODE, config: CsvConfig = CONFIG): List<T> =
        load(File(path), entityClass, encode, config)

    fun load(path: Path, entityClass: Class<T>, encode: String = ENCODE, config: CsvConfig = CONFIG): List<T> =
        load(path.toFile(), entityClass, encode, config)

    fun load(file: File, entityClass: Class<T>, encode: String = ENCODE, config: CsvConfig = CONFIG): List<T> =
        try {
            Csv.load(file, encode, config, CsvEntityListHandler<T>(entityClass))
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }

}