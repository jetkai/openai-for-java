package io.github.jetkai.openai.api.data.embedding.response.usage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Optional;

/**
 * EmbeddingResponseUsageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = EmbeddingResponseUsageData.Builder.class)
public abstract class EmbeddingResponseUsageData {

    public EmbeddingResponseUsageData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("total_tokens")
        Builder setTotalTokens(int totalTokens);
        @JsonProperty("prompt_tokens")
        Builder setPromptTokens(int promptTokens);
        EmbeddingResponseUsageData build();

        @JsonCreator
        static Builder create() {
            return new EmbeddingResponseUsageBuilderImpl();
        }

    }

    public abstract int getTotalTokens();
    public abstract int getPromptTokens();

    public abstract Optional<Integer> totalTokens();
    public abstract Optional<Integer> promptTokens();


}