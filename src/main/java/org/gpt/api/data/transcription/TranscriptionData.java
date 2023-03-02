package org.gpt.api.data.transcription;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.nio.file.Path;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class TranscriptionData {

    private Path file;
    private String model;
    private String prompt;
    @JsonProperty("response_format")
    private String responseFormat;
    private double temperature;
    private String language;

    public TranscriptionData() { }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setFile(Path file) {
        this.file = file;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getModel() {
        return model;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public String getPrompt() {
        return prompt;
    }

    public double getTemperature() {
        return temperature;
    }

    public Path getFile() {
        return file;
    }

    public String getLanguage() {
        return language;
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
