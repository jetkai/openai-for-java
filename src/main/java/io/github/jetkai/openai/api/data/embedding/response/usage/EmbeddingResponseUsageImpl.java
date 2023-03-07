package io.github.jetkai.openai.api.data.embedding.response.usage;

import java.util.Optional;

/**
 * EmbeddingResponseUsageImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class EmbeddingResponseUsageImpl extends EmbeddingResponseUsageData {

    private final int totalTokens;
    private final int promptTokens;

    static EmbeddingResponseUsageImpl create(EmbeddingResponseUsageBuilderImpl builder) {
        return new EmbeddingResponseUsageImpl(builder);
    }

    private EmbeddingResponseUsageImpl(EmbeddingResponseUsageBuilderImpl builder) {
        this.totalTokens = builder.totalTokens;
        this.promptTokens = builder.promptTokens;
    }

    @Override
    public int getTotalTokens() {
        return this.totalTokens;
    }

    @Override
    public int getPromptTokens() {
        return this.promptTokens;
    }

    @Override
    public Optional<Integer> totalTokens() {
        return Optional.of(this.totalTokens);
    }

    @Override
    public Optional<Integer> promptTokens() {
        return Optional.of(this.promptTokens);
    }

}