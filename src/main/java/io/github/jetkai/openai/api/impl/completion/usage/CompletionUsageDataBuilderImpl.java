package io.github.jetkai.openai.api.impl.completion.usage;

import io.github.jetkai.openai.api.data.completion.usage.CompletionUsageData;

/**
 * CompletionUsageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CompletionUsageDataBuilderImpl implements CompletionUsageData.Builder {

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
        return CompletionUsageDataImpl.create(this);
    }

}