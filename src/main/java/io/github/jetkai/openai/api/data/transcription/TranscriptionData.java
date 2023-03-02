package io.github.jetkai.openai.api.data.transcription;


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

    /*
     * file
     * string
     * Required
     *
     * The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     */
    private Path file;

    /*
     * model
     * string
     * Required
     *
     * ID of the model to use. Only whisper-1 is currently available.
     */
    private String model;

    /*
     * prompt
     * string
     * Optional
     *
     * An optional text to guide the model's style or continue a previous audio segment.
     * The prompt should match the audio language.
     */
    private String prompt;

    /*
     * response_format
     * string
     * Optional
     * Defaults to json
     *
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     */
    @JsonProperty("response_format")
    private String responseFormat;

    /*
     * temperature
     * number
     * Optional
     * Defaults to 0
     *
     * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will
     * use log probability to automatically increase the temperature until certain thresholds are hit.
     */
    private double temperature;

    /*
     * language
     * string
     * Optional
     *
     * The language of the input audio. Supplying the input language in ISO-639-1 format
     * will improve accuracy and latency.
     */
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
