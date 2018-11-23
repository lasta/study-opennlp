package com.lasta.data

import com.lasta.data.entity.ArticleEntity
import java.io.File
import java.io.IOException
import kotlin.system.exitProcess

/**
 * Test to load tsv file.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        test()
    }

    fun test() {
        val file = File(ClassLoader.getSystemResource("data/article.tsv").toURI())
        val data: List<ArticleEntity> = try {
            CacheLoader<ArticleEntity>().load(file, ArticleEntity::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            exitProcess(1)
        }

        with(data[0]) {
            println(id)
            println(title)
            println(body.replace("＼", "\n"))
            println(category.split("|").joinToString(", "))
            println(lang)
        }
    }

}