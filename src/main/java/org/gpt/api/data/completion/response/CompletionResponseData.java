package org.gpt.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class CompletionResponseData {

    private String id;
    private String object;
    private String model;
    private List<CompletionChoice> choices;
    private CompletionUsage usage;

    public CompletionResponseData() { }

    public CompletionResponseData(String id, String object, String model,
                                  List<CompletionChoice> choices, CompletionUsage usage) {
        this.id = id;
        this.object = object;
        this.choices = choices;
        this.usage = usage;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChoices(List<CompletionChoice> choices) {
        this.choices = choices;
    }

    public void setUsage(CompletionUsage usage) {
        this.usage = usage;
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

    public List<CompletionChoice> getChoices() {
        return choices;
    }

    public CompletionUsage getUsage() {
        return usage;
    }

    @JsonIgnore
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}