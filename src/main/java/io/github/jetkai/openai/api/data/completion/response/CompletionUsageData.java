package io.github.jetkai.openai.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * CompletionUsageData
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
class CompletionUsageData {

    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;

    public CompletionUsageData() { }

    public CompletionUsageData setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
        return this;
    }

    public CompletionUsageData setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
        return this;
    }

    public CompletionUsageData setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}