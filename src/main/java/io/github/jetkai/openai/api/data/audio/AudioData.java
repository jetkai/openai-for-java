package io.github.jetkai.openai.api.data.audio;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * AudioData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class AudioData {

    public AudioData() { }

    public static AudioData.Builder builder() {
        return new AudioBuilderImpl();
    }

    public static AudioData create(Path filePath) {
        return builder().setFilePath(filePath)
                .setModel("whisper-1")
                .build();
    }

    public static AudioData create(Path filePath, Locale locale) {
        return builder().setFilePath(filePath)
                .setModel("whisper-1")
                .setLanguage(locale.getLanguage())
                .build();
    }

    public static AudioData create(Path filePath, String locale) {
        return builder().setFilePath(filePath)
                .setModel("whisper-1")
                .setLanguage(locale)
                .build();
    }

    public interface Builder {
        Builder setModel(String model);
        Builder setPrompt(List<String> prompt);
        Builder setPrompt(String prompt);
        Builder setResponseFormat(String responseFormat);
        Builder setTemperature(double temperature);
        Builder setLanguage(String language);
        Builder setFilePath(String filePath);
        Builder setFilePath(Path filePath);
        AudioData build();
    }

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. Only whisper-1 is currently available.
     * @return model
     */
    @JsonProperty("model")
    public abstract String getModel();

    /**
     * prompt
     * string
     * Optional
     * <p>
     * An optional text to guide the model's style or continue a previous audio segment.
     * The prompt should match the audio language.
     * @return prompt
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("prompt")
    public abstract List<String> getPrompt();

    /**
     * response_format
     * string
     * Optional
     * Defaults to json
     * <p>
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     * @return responseFormat
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("response_format")
    public abstract String getResponseFormat();

    /**
     * temperature
     * number
     * Optional
     * Defaults to 0
     * <p>
     * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will
     * use log probability to automatically increase the temperature until certain thresholds are hit.
     * @return temperature
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("temperature")
    public abstract double getTemperature();

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
     * @return language
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("language")
    public abstract String getLanguage();

    /**
     * file
     * string
     * Required
     * <p>
     * The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * @return file
     */
    @JsonProperty("file")
    public abstract Path getFilePath();

    public abstract Optional<String> model();
    public abstract Optional<List<String>> prompt();
    public abstract Optional<String> responseFormat();
    public abstract Optional<Double> temperature();
    public abstract Optional<String> language();
    public abstract Optional<Path> filePath();

}
