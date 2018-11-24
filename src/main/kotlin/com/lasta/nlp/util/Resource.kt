package com.lasta.nlp.util

import com.atilika.kuromoji.TokenizerBase
import com.atilika.kuromoji.ipadic.Tokenizer
import com.lasta.nlp.languagedetector.entity.ArticleEntity
import java.io.File

object Resource {
    /**
     * Training data.
     */
    val trainerArticles: List<ArticleEntity> by lazy {
        CacheLoader<ArticleEntity>().load(
            File(ClassLoader.getSystemResource("data/language-detector/trainer_article.tsv").toURI()),
            ArticleEntity::class.java
        )
    }

    /**
     * Examinee data.
     */
    val examineeArticle: List<ArticleEntity> by lazy {
        CacheLoader<ArticleEntity>().load(
            File(ClassLoader.getSystemResource("data/language-detector/examinee_article.tsv").toURI()),
            ArticleEntity::class.java
        )
    }

    /**
     * Common use Japanese tokenizer.
     */
    val tokenizer: Tokenizer by lazy {
        Tokenizer.Builder().mode(TokenizerBase.Mode.NORMAL).build()
    }
}