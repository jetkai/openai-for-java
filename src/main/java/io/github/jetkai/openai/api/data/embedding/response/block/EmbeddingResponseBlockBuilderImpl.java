package io.github.jetkai.openai.api.data.embedding.response.block;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * EmbeddingResponseBlockData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class EmbeddingResponseBlockBuilderImpl implements EmbeddingResponseBlockData.Builder {

    String object;
    List<Float> embedding;
    int index;

    @Override
    public EmbeddingResponseBlockData.Builder setObject(String object) {
        requireNonNull(object);
        this.object = object;
        return this;
    }

    @Override
    public EmbeddingResponseBlockData.Builder setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public EmbeddingResponseBlockData.Builder setEmbedding(List<Float> embedding) {
        requireNonNull(embedding);
        this.embedding = embedding;
        return this;
    }

    @Override
    public EmbeddingResponseBlockData build() {
       return EmbeddingResponseBlockImpl.create(this);
    }

}