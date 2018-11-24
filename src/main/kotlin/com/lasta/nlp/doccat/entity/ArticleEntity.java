package com.lasta.nlp.doccat.entity;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CsvEntity(header = false)
public class ArticleEntity {
    @CsvColumn(position = 0, required = true)
    private String id;
    @CsvColumn(position = 1, required = true)
    private String title;
    @CsvColumn(position = 2, required = true)
    private String body;
    @CsvColumn(position = 3, required = false)
    private String labelId;
    @CsvColumn(position = 4, required = true)
    private String type;
    @CsvColumn(position = 5, required = true)
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

    @Nullable
    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    @NotNull
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
