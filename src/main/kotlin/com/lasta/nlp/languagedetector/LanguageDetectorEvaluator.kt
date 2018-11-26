package com.lasta.nlp.languagedetector

import com.lasta.nlp.languagedetector.entity.ArticleEntity
import com.lasta.nlp.util.EvaluateResult
import com.lasta.nlp.util.Resource
import com.lasta.nlp.util.write
import opennlp.tools.langdetect.Language
import opennlp.tools.langdetect.LanguageDetector
import opennlp.tools.langdetect.LanguageDetectorME
import opennlp.tools.langdetect.LanguageDetectorModel
import java.io.IOException
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

        val results: List<EvaluateResult> = articles.mapIndexed { idx, article ->
            if (idx % 1000 == 0) {
                println("Progress... $idx")
            }
            predict(article, model)
        }

        printResult(results)
        write(results, EvaluateResult::class.java, Paths.get("data/language-detector/result.tsv"))

    }

    private fun predict(article: ArticleEntity, model: LanguageDetector): EvaluateResult = EvaluateResult(
        expect = article.lang,
        actual = article.body.predictLanguage(model),
        body = article.body
    )

    private fun String.predictLanguage(model: LanguageDetector): String {
        val bestLanguage: Language = model.predictLanguage(this)
        return Constant.ISO639_3to1[bestLanguage.lang] ?: "Unknown"
    }

    private fun printResult(results: List<EvaluateResult>) {
        val examineesCount: Int = results.size
        val correctAnswerCount: Int = results.filter { it.actual == it.expect }.size

        println("Examinees count: $examineesCount")
        println("Correct answer count: $correctAnswerCount")
    }

}
