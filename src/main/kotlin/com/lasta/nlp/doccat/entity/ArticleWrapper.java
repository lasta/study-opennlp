package com.lasta.nlp.doccat.entity;

import com.orangesignal.csv.annotation.CsvColumn;
import com.orangesignal.csv.annotation.CsvEntity;
import org.jetbrains.annotations.NotNull;

@CsvEntity()
public class ArticleWrapper {
    @CsvColumn(position = 0, required = true)
    private String label;
    @CsvColumn(position = 1, required = true)
    private String body;

    public ArticleWrapper() {
    }

    @NotNull
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @NotNull
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

