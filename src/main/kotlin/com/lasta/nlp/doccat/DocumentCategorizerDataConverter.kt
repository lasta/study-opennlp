package com.lasta.nlp.doccat

import com.lasta.nlp.doccat.entity.ArticleEntity
import com.lasta.nlp.doccat.entity.ArticleWrapper
import com.lasta.nlp.util.CacheLoader
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

object DocumentCategorizerDataConverter {
    @JvmStatic
    fun main(args: Array<String>) {
        val loader: CacheLoader<ArticleEntity> = CacheLoader()
        val input: List<ArticleEntity> = try {
            loader.load(
                file = File(ClassLoader.getSystemResource("data/doccat/article.tsv").toURI()),
                entityClass = ArticleEntity::class.java
            )
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }

        val trainingData: List<ArticleWrapper> =
            input.filter { it.type == "2" }
                .filter { !it.labelId.isNullOrBlank() }
                .filter { it.lang == "ja" }
                .map { it.convert() }
        val examineeData: List<ArticleWrapper> =
            input.filter { it.type == "1" }
                .filter { !it.labelId.isNullOrBlank() }
                .filter { it.lang == "ja" }
                .map { it.convert() }

        write(trainingData, "data/doccat/trainer.txt")
        write(examineeData, "data/doccat/examinee.txt")
    }

    private fun ArticleEntity.convert(): ArticleWrapper {
        val output = ArticleWrapper()
        // labelId is already not null at 25, 27
        output.label = this.labelId!!.split("|")[0]
        output.body = this.body.replace("＼", "")
        return output
    }

    private fun write(data: List<ArticleWrapper>, path: String) {
        try {
            PrintWriter(Files.newBufferedWriter(Paths.get(path))).use { printer ->
                data.forEach { row ->
                    printer.println("${row.label}\t${row.body}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}