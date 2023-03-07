package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * EmbeddingResponseUsageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
class EmbeddingResponseUsageData {

    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;

    public EmbeddingResponseUsageData() { }

    public EmbeddingResponseUsageData setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    public EmbeddingResponseUsageData setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
        return this;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

}