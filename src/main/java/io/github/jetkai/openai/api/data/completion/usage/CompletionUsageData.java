package io.github.jetkai.openai.api.data.completion.usage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.impl.completion.usage.CompletionUsageDataBuilderImpl;

import java.util.Optional;

/**
 * CompletionUsageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = CompletionUsageData.Builder.class)
@SuppressWarnings("unused")
public abstract class CompletionUsageData {

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {

        @JsonProperty("completion_tokens")
        Builder setCompletionTokens(int completionTokens);
        @JsonProperty("prompt_tokens")
        Builder setPromptTokens(int promptTokens);
        @JsonProperty("total_tokens")
        Builder setTotalTokens(int totalTokens);

        CompletionUsageData build();

        @JsonCreator
        static Builder create() {
            return new CompletionUsageDataBuilderImpl();
        }
    }

    @JsonProperty("completion_tokens")
    public abstract int getCompletionTokens();
    @JsonProperty("prompt_tokens")
    public abstract int getPromptTokens();
    @JsonProperty("total_tokens")
    public abstract int getTotalTokens();

    public abstract Optional<Integer> completionTokens();
    public abstract Optional<Integer> promptTokens();
    public abstract Optional<Integer> totalTokens();

}