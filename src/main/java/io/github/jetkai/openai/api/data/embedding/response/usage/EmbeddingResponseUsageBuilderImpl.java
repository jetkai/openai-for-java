package io.github.jetkai.openai.api.data.embedding.response.usage;

/**
 * EmbeddingResponseUsageBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class EmbeddingResponseUsageBuilderImpl implements EmbeddingResponseUsageData.Builder {

    int totalTokens;
    int promptTokens;

    @Override
    public EmbeddingResponseUsageData.Builder setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    @Override
    public EmbeddingResponseUsageData.Builder setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
        return this;
    }

    @Override
    public EmbeddingResponseUsageData build() {
        return EmbeddingResponseUsageImpl.create(this);
    }

}