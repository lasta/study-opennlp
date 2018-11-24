package com.lasta.nlp.languagedetector.entity;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;
import org.jetbrains.annotations.NotNull;

@CsvEntity(header = false)
public class ArticleEntity {
    /**
     * 記事ID
     */
    @CsvColumn(position = 0, required = true)
    private String id;
    /**
     * 記事タイトル
     */
    @CsvColumn(position = 1, required = true)
    private String title;
    /**
     * 記事本文
     */
    @CsvColumn(position = 2, required = true)
    private String body;
    /**
     * カテゴリID
     */
    @CsvColumn(position = 3, required = true)
    private String category;
    /**
     * 言語コード
     */
    @CsvColumn(position = 4, required = true)
    private String lang;

    public ArticleEntity() {
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @NotNull
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotNull
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
