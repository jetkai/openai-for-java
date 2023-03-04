package io.github.jetkai.openai.api.data.translation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.URI;
import java.nio.file.Path;

/**
 * TranslationData
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
public class TranslationData {

    /**
     * file
     * string
     * Required
     * <p>
     * The audio file to translate, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     */
    private Path file;

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. Only whisper-1 is currently available.
     */
    private String model;

    /**
     * prompt
     * string
     * Optional
     * <p>
     * An optional text to guide the model's style or continue a previous audio segment.
     * The prompt should be in English.
     */
    private String prompt;

    /**
     * response_format
     * string
     * Optional
     * Defaults to json
     * <p>
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     */
    @JsonProperty("response_format")
    private String responseFormat;

    /**
     * temperature
     * number
     * Optional
     * Defaults to 0
     * <p>
     * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will
     * use log probability to automatically increase the temperature until certain thresholds are hit.
     */
    private double temperature;

    //Not documented, but defaults to English - Can be set to French by setting language as "fr"
    private String language;

    public TranslationData() { }

    public TranslationData setLanguage(String language) {
        this.language = language;
        return this;
    }

    public TranslationData setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
        return this;
    }

    public TranslationData setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public TranslationData setFile(String file) {
        this.file = Path.of(URI.create(file));
        return this;
    }

    public TranslationData setFile(Path file) {
        this.file = file;
        return this;
    }

    public TranslationData setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public TranslationData setModel(String model) {
        this.model = model;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public Path getFile() {
        return file;
    }

    public String getModel() {
        return model;
    }

    public String getLanguage() {
        return language;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
