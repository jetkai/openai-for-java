package io.github.jetkai.openai.api.impl.audio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.audio.AudioData;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * AudioImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.1
 * {@code - 05/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class AudioImpl extends AudioData {

    /**
     * file
     * string
     * Required
     * <p>
     * The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     */
    private final Path file;

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. Only whisper-1 is currently available.
     */
    private final String model;

    /**
     * prompt
     * string
     * Optional
     * <p>
     * An optional text to guide the model's style or continue a previous audio segment.
     * The prompt should match the audio language.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private final List<String> prompt;

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
    private final String responseFormat;

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
    private final double temperature;

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
    private final String language;

    static AudioImpl create(AudioBuilderImpl builder) {
        return new AudioImpl(builder);
    }

    private AudioImpl(AudioBuilderImpl builder) {
        this.model = builder.model;
        this.language = builder.language;
        this.file = builder.file;
        this.temperature = builder.temperature;
        this.prompt = builder.prompt;
        this.responseFormat = builder.responseFormat;
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<List<String>> prompt() {
        return Optional.ofNullable(this.prompt);
    }

    @Override
    public Optional<String> responseFormat() {
        return Optional.ofNullable(this.responseFormat);
    }

    @Override
    public Optional<Double> temperature() {
        return Optional.of(this.temperature);
    }

    @Override
    public Optional<String> language() {
        return Optional.ofNullable(this.language);
    }

    @Override
    public Optional<Path> filePath() {
        return Optional.ofNullable(this.file);
    }

}
