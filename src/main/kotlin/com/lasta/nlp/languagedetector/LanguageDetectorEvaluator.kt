package com.lasta.nlp.languagedetector

import com.lasta.nlp.languagedetector.entity.ArticleEntity
import com.lasta.nlp.util.Resource
import opennlp.tools.langdetect.Language
import opennlp.tools.langdetect.LanguageDetector
import opennlp.tools.langdetect.LanguageDetectorME
import opennlp.tools.langdetect.LanguageDetectorModel
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

object LanguageDetectorEvaluator {
    private const val MODEL_PATH: String = "data/language-detector/language_model.bin"

    @JvmStatic
    fun main(args: Array<String>) {
        val model: LanguageDetector = try {
            Files.newInputStream(Paths.get(MODEL_PATH)).use { stream ->
                LanguageDetectorME(LanguageDetectorModel(stream))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }

        val articles: List<ArticleEntity> = Resource.examineeArticle

        val results: List<Result> = articles.mapIndexed { idx, article ->
            if (idx % 1000 == 0) {
                println("Progress... $idx")
            }
            predict(article, model)
        }

        printResult(results)
        exportResult(results, "data/language-detector/result.tsv")

    }

    data class Result(val expected: String, val actual: String, val sentence: String)

    private fun predict(article: ArticleEntity, model: LanguageDetector): Result = Result(
        expected = article.lang,
        actual = article.body.predictLanguage(model),
        sentence = article.body
    )

    private fun String.predictLanguage(model: LanguageDetector): String {
        val bestLanguage: Language = model.predictLanguage(this)
        return Constant.ISO639_3to1[bestLanguage.lang] ?: "Unknown"
    }

    private fun printResult(results: List<Result>) {
        val examineesCount: Int = results.size
        val correctAnswerCount: Int = results.filter { it.actual == it.expected }.size

        println("Examinees count: $examineesCount")
        println("Correct answer count: $correctAnswerCount")
    }

    private fun exportResult(results: List<Result>, path: String) {
        try {
            PrintWriter(Files.newBufferedWriter(Paths.get(path))).use { printer ->
                printer.println("Expected\tActual\tSentence")
                results.forEach { result ->
                    printer.println("${result.expected}\t${result.actual}\t${result.sentence}")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}