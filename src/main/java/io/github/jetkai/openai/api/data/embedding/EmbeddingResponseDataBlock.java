package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * EmbeddingResponseDataBlock
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
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