package com.lasta.nlp.util

import java.io.IOException
import java.io.PrintWriter
import java.lang.reflect.Field
import java.nio.file.Files
import java.nio.file.Path

fun <T> write(data: List<T>, clazz: Class<T>, path: Path, separator: String = "\t") {

    val fields: Array<Field> = try {
        clazz.fields
    } catch (e: SecurityException) {
        e.printStackTrace()
        return
    }

    try {
        PrintWriter(Files.newOutputStream(path)).use { printer ->
            printer.println(fields.joinToString(separator) { field -> field.name })
            data.forEach { elem ->
                val row = fields.joinToString(separator) { field -> field.get(elem).toString() }
                printer.println(row)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

data class EvaluateResult(
    val expect: String,
    val actual: String,
    val body: String
)
