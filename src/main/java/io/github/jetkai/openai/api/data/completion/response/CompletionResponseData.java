package io.github.jetkai.openai.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.List;

/**
 * CompletionResponseData
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
public class CompletionResponseData {

    private String id;
    private String object;
    private String model;
    private List<CompletionChoiceData> choices;
    private CompletionUsageData usage;

    public CompletionResponseData() { }

    public CompletionResponseData setModel(String model) {
        this.model = model;
        return this;
    }

    public CompletionResponseData setObject(String object) {
        this.object = object;
        return this;
    }

    public CompletionResponseData setId(String id) {
        this.id = id;
        return this;
    }

    public CompletionResponseData setChoices(List<CompletionChoiceData> choices) {
        this.choices = choices;
        return this;
    }

    public CompletionResponseData setUsage(CompletionUsageData usage) {
        this.usage = usage;
        return this;
    }

    public String getModel() {
        return model;
    }

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public List<CompletionChoiceData> getChoices() {
        return choices;
    }

    public CompletionUsageData getUsage() {
        return usage;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}