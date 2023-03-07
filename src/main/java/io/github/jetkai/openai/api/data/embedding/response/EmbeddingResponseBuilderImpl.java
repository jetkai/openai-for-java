package io.github.jetkai.openai.api.data.embedding.response;

import io.github.jetkai.openai.api.data.embedding.response.block.EmbeddingResponseBlockData;
import io.github.jetkai.openai.api.data.embedding.response.usage.EmbeddingResponseUsageData;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * EmbeddingResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class EmbeddingResponseBuilderImpl implements EmbeddingResponseData.Builder {

    String object;
    List<EmbeddingResponseBlockData> data;
    String model;
    EmbeddingResponseUsageData usage;

    @Override
    public EmbeddingResponseData.Builder setModel(String model) {
        requireNonNull(model);
        this.model = model;
        return this;
    }

    @Override
    public EmbeddingResponseData.Builder setData(List<EmbeddingResponseBlockData> data) {
        requireNonNull(data);
        this.data = data;
        return this;
    }

    @Override
    public EmbeddingResponseData.Builder setObject(String object) {
        requireNonNull(object);
        this.object = object;
        return this;
    }

    @Override
    public EmbeddingResponseData.Builder setUsage(EmbeddingResponseUsageData usage) {
        requireNonNull(usage);
        this.usage = usage;
        return this;
    }

    @Override
    public EmbeddingResponseData build() {
        return EmbeddingResponseImpl.create(this);
    }

}
