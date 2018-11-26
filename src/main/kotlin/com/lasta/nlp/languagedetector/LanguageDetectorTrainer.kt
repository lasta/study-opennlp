package com.lasta.nlp.languagedetector

import com.lasta.nlp.util.Resource
import java.io.IOException
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

object LanguageDetectorTrainer {
    @JvmStatic
    fun main(args: Array<String>) {
        val trainingData: List<String> = createTrainingData()
        trainingData.toFile("data/language-detector/trainer.txt")
    }

    private fun createTrainingData(): List<String> =
        Resource.trainerArticles.map { article ->
            // und : unknown language code
            val lang: String = Constant.ISO639_1to3[article.lang] ?: "und"
            return@map "$lang\t${article.body.replace("＼", "")}"
        }

    private fun List<String>.toFile(path: String) {
        try {
            PrintWriter(Files.newBufferedWriter(Paths.get(path))).use { printer ->
                this.forEach { line -> printer.println(line) }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
