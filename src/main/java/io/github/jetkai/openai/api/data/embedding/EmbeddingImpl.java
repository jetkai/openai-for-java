package io.github.jetkai.openai.api.data.embedding;

import java.util.List;
import java.util.Optional;

/**
 * EmbeddingImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class EmbeddingImpl extends EmbeddingData {

    private final String model;
    private final List<String> input;
    private final String user;

    static EmbeddingImpl create(EmbeddingBuilderImpl builder) {
        return new EmbeddingImpl(builder);
    }

    private EmbeddingImpl(EmbeddingBuilderImpl builder) {
        this.model = builder.model;
        this.user = builder.user;
        this.input = builder.input;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public List<String> getInput() {
        return this.input;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<List<String>> input() {
        return Optional.ofNullable(this.input);
    }

    @Override
    public Optional<String> user() {
        return Optional.ofNullable(this.user);
    }

}
