package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

/**
 * EmbeddingResponseBlockData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public class EmbeddingResponseBlockData {

    private String object;
    private List<Float> embedding;
    private int index;

    public EmbeddingResponseBlockData() { }

    public EmbeddingResponseBlockData setObject(String object) {
        this.object = object;
        return this;
    }

    public EmbeddingResponseBlockData setIndex(int index) {
        this.index = index;
        return this;
    }

    public EmbeddingResponseBlockData setEmbedding(List<Float> embedding) {
        this.embedding = embedding;
        return this;
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