package io.github.jetkai.openai.api.data.embedding.response.block;

import java.util.List;
import java.util.Optional;

/**
 * EmbeddingResponseBlockData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class EmbeddingResponseBlockImpl extends EmbeddingResponseBlockData {

    private final String object;
    private final List<Float> embedding;
    private final int index;

    static EmbeddingResponseBlockImpl create(EmbeddingResponseBlockBuilderImpl builder) {
        return new EmbeddingResponseBlockImpl(builder);
    }

    private EmbeddingResponseBlockImpl(EmbeddingResponseBlockBuilderImpl builder) {
        this.object = builder.object;
        this.embedding = builder.embedding;
        this.index = builder.index;
    }


    @Override
    public String getObject() {
        return this.object;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public List<Float> getEmbedding() {
        return this.embedding;
    }

    @Override
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<Integer> index() {
        return Optional.of(this.index);
    }

    @Override
    public Optional<List<Float>> embedding() {
        return Optional.ofNullable(this.embedding);
    }
}