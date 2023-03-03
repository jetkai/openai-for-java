package io.github.jetkai.openai.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * CompletionChoice
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
public class CompletionChoice {

    private String id;
    private String object;
    private int created;
    private CompletionMessage message;
    private String text;
    private int index;
    private String logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;

    public CompletionChoice() { }

    public CompletionChoice(String id, String object, int created, String text, int index,
                            CompletionMessage message, String logprobs, String finishReason) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.text = text;
        this.index = index;
        this.logprobs = logprobs;
        this.finishReason = finishReason;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setMessage(CompletionMessage message) {
        this.message = message;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setText(String text) {
        this.text = text;
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

    public CompletionMessage getMessage() {
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