package io.github.jetkai.openai.api.data.models;

import io.github.jetkai.openai.api.data.model.ModelData;

import java.util.Optional;

/**
 * ModelsResponseImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ModelsResponseImpl extends ModelsResponseData {

    private final String object;
    private final ModelData[] data;

    static ModelsResponseImpl create(ModelsResponseBuilderImpl builder) {
        return new ModelsResponseImpl(builder);
    }

    private ModelsResponseImpl(ModelsResponseBuilderImpl builder) {
        this.object = builder.object;
        this.data = builder.data;
    }

    @Override
    public String getObject() {
        return this.object;
    }

    @Override
    public ModelData[] getData() {
        return this.data;
    }

    @Override
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<ModelData[]> data() {
        return Optional.ofNullable(this.data);
    }

}
