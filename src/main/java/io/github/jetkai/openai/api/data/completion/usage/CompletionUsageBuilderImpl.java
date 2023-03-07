package io.github.jetkai.openai.api.data.completion.usage;

/**
 * CompletionUsageBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class CompletionUsageBuilderImpl implements CompletionUsageData.Builder {

    int promptTokens;
    int completionTokens;
    int totalTokens;

    @Override
    public CompletionUsageData.Builder setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
        return this;
    }

    @Override
    public CompletionUsageData.Builder setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
        return this;
    }

    @Override
    public CompletionUsageData.Builder setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    @Override
    public CompletionUsageData build() {
        return CompletionUsageImpl.create(this);
    }

}