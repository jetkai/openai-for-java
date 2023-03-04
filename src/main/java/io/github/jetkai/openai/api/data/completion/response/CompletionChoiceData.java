package io.github.jetkai.openai.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * CompletionChoiceData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class CompletionChoiceData {

    private String id;
    private String object;
    private int created;
    private CompletionMessageData message;
    private String text;
    private int index;
    private String logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;

    public CompletionChoiceData() { }

    public CompletionChoiceData setId(String id) {
        this.id = id;
        return this;
    }

    public CompletionChoiceData setCreated(int created) {
        this.created = created;
        return this;
    }

    public CompletionChoiceData setObject(String object) {
        this.object = object;
        return this;
    }

    public CompletionChoiceData setMessage(CompletionMessageData message) {
        this.message = message;
        return this;
    }

    public CompletionChoiceData setLogprobs(String logprobs) {
        this.logprobs = logprobs;
        return this;
    }

    public CompletionChoiceData setFinishReason(String finishReason) {
        this.finishReason = finishReason;
        return this;
    }

    public CompletionChoiceData setIndex(int index) {
        this.index = index;
        return this;
    }

    public CompletionChoiceData setText(String text) {
        this.text = text;
        return this;
    }

    public String getObject() {
        return object;
    }

    public int getCreated() {
        return created;
    }

    public String getId() {
        return id;
    }

    public CompletionMessageData getMessage() {
        return message;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public int getIndex() {
        return index;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public String getText() {
        return text;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}