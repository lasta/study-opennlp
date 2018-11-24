package com.lasta.nlp.doccat

import com.lasta.nlp.doccat.entity.ArticleWrapper
import com.lasta.nlp.util.CacheLoader
import opennlp.tools.doccat.DoccatModel
import opennlp.tools.doccat.DocumentCategorizerME
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

object DocumentCategorizerEvaluator {
    private const val MODEL_PATH = "data/doccat/model.bin"
    private const val EXAMINEE_PATH = "data/doccat/examinee.txt"
    private const val RESULT_PATH = "data/doccat/result.tsv"

    @JvmStatic
    fun main(args: Array<String>) {
        val model: DocumentCategorizerME = try {
            Files.newInputStream(Paths.get(MODEL_PATH)).use { stream ->
                DocumentCategorizerME(DoccatModel(stream))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }

        val examinee: List<ArticleWrapper> = try {
            CacheLoader<ArticleWrapper>().load(
                Paths.get(EXAMINEE_PATH).toFile(),
                ArticleWrapper::class.java
            )
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }

        val results: List<Result> = examinee.map { it.predictCategory(model) }
        val correctCount = results.filter { it.expect == it.actual }.size
        println("Examinee Count: ${examinee.size}")
        println("Correct Count: $correctCount")

        write(results, RESULT_PATH)
    }

    private fun ArticleWrapper.predictCategory(model: DocumentCategorizerME): Result =
        Result(
            expect = this.label,
            actual = this.body.predict(model),
            body = this.body
        )

    private fun String.predict(model: DocumentCategorizerME): String =
        model.getBestCategory(model.categorize(arrayOf(this)))

    data class Result(
        val expect: String,
        val actual: String,
        val body: String
    )

    private fun write(data: List<Result>, path: String) {
        try {
            PrintWriter(Files.newBufferedWriter(Paths.get(path))).use { printer ->
                printer.println("expect\tactual\tbody")
                data.forEach { row ->
                    printer.println("${row.expect}\t${row.actual}\t${row.body}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}