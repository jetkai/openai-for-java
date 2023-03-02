package io.github.jetkai.chatgpt.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class EmbeddingResponseDataBlock {

    private String object;
    private List<Float> embedding;
    private int index;

    public EmbeddingResponseDataBlock() { }

    public void setObject(String object) {
        this.object = object;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setEmbedding(List<Float> embedding) {
        this.embedding = embedding;
    }

    public String getObject() {
        return object;
    }

    public int getIndex() {
        return index;
    }

    public List<Float> getEmbedding() {
        return embedding;
    }

}