package io.github.jetkai.openai.api.data.audio;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.URI;
import java.nio.file.Path;
import java.util.Locale;

/**
 * AudioTranscriptionData
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
public class AudioData {

    /**
     * file
     * string
     * Required
     * <p>
     * The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
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
     * The prompt should match the audio language.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String prompt;

    /**
     * response_format
     * string
     * Optional
     * Defaults to json
     * <p>
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
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
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double temperature;

    /**
     * language
     * string
     * Optional
     * <p>
     * The language of the input audio. Supplying the input language in ISO-639-1 format
     * will improve accuracy and latency.
     * <p>
     *     <a href="https://github.com/openai/whisper#available-models-and-languages">List of supported languages</a>
     * </p>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String language;

    public AudioData() { }

    public static AudioData create() {
        return new AudioData();
    }

    public static AudioData create(Path filePath) {
        return new AudioData()
                .setFile(filePath)
                .setModel("whisper-1");
    }

    public static AudioData create(Path filePath, Locale locale) {
        return new AudioData()
                .setFile(filePath)
                .setModel("whisper-1")
                .setLanguage(locale.getLanguage());
    }

    public static AudioData create(Path filePath, String locale) {
        return new AudioData()
                .setFile(filePath)
                .setModel("whisper-1")
                .setLanguage(locale);
    }

    public AudioData setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
        return this;
    }

    public AudioData setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public AudioData setModel(String model) {
        this.model = model;
        return this;
    }

    public AudioData setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public AudioData setFile(String file) {
        this.file = Path.of(URI.create(file));
        return this;
    }

    public AudioData setFile(Path file) {
        this.file = file;
        return this;
    }

    public AudioData setLanguage(String language) {
        this.language = language;
        return this;
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
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}