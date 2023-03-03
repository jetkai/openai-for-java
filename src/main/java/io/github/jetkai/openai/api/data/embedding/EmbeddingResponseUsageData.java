package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * EmbeddingResponseUsageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
class EmbeddingResponseUsageData {

    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;

    public EmbeddingResponseUsageData() { }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

}