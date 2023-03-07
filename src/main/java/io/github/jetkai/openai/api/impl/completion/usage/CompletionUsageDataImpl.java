package io.github.jetkai.openai.api.impl.completion.usage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jetkai.openai.api.data.completion.usage.CompletionUsageData;

import java.util.Optional;

/**
 * CompletionUsageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CompletionUsageDataImpl extends CompletionUsageData {

    @JsonProperty("prompt_tokens")
    private final int promptTokens;
    @JsonProperty("completion_tokens")
    private final int completionTokens;
    @JsonProperty("total_tokens")
    private final int totalTokens;

    static CompletionUsageDataImpl create(CompletionUsageDataBuilderImpl builder) {
        return new CompletionUsageDataImpl(builder);
    }

    private CompletionUsageDataImpl(CompletionUsageDataBuilderImpl builder) {
        this.promptTokens = builder.promptTokens;
        this.completionTokens = builder.completionTokens;
        this.totalTokens = builder.totalTokens;
    }

    @Override
    public int getCompletionTokens() {
        return this.completionTokens;
    }

    @Override
    public int getPromptTokens() {
        return this.promptTokens;
    }

    @Override
    public int getTotalTokens() {
        return this.totalTokens;
    }

    @Override
    public Optional<Integer> completionTokens() {
        return Optional.of(this.completionTokens);
    }

    @Override
    public Optional<Integer> promptTokens() {
        return Optional.of(this.promptTokens);
    }

    @Override
    public Optional<Integer> totalTokens() {
        return Optional.of(this.totalTokens);
    }

}