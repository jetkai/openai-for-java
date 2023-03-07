package io.github.jetkai.openai.api.data.embedding.response;

import io.github.jetkai.openai.api.data.embedding.response.block.EmbeddingResponseBlockData;
import io.github.jetkai.openai.api.data.embedding.response.usage.EmbeddingResponseUsageData;

import java.util.List;
import java.util.Optional;

/**
 * EmbeddingResponseImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class EmbeddingResponseImpl extends EmbeddingResponseData {

    private final String object;
    private final List<EmbeddingResponseBlockData> data;
    private final String model;
    private final EmbeddingResponseUsageData usage;

    static EmbeddingResponseImpl create(EmbeddingResponseBuilderImpl builder) {
        return new EmbeddingResponseImpl(builder);
    }

    private EmbeddingResponseImpl(EmbeddingResponseBuilderImpl builder) {
        this.model = builder.model;
        this.object = builder.object;
        this.data = builder.data;
        this.usage = builder.usage;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getObject() {
        return this.object;
    }

    @Override
    public List<EmbeddingResponseBlockData> getData() {
        return this.data;
    }

    @Override
    public EmbeddingResponseUsageData getUsage() {
        return this.usage;
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<List<EmbeddingResponseBlockData>> data() {
        return Optional.ofNullable(this.data);
    }

    @Override
    public Optional<EmbeddingResponseUsageData> usage() {
        return Optional.ofNullable(this.usage);
    }

}
