package io.github.jetkai.openai.api.data.models;

import io.github.jetkai.openai.api.data.model.ModelData;

import static java.util.Objects.requireNonNull;

/**
 * ModelsResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ModelsResponseBuilderImpl implements ModelsResponseData.Builder {

    String object;
    ModelData[] data;

    @Override
    public ModelsResponseData.Builder setObject(String object) {
        requireNonNull(object);
        this.object = object;
        return this;
    }

    @Override
    public ModelsResponseData.Builder setData(ModelData[] data) {
        requireNonNull(data);
        this.data = data;
        return this;
    }

    @Override
    public ModelsResponseData build() {
        return ModelsResponseImpl.create(this);
    }
}
