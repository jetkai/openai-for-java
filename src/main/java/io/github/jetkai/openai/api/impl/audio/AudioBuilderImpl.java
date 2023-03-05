package io.github.jetkai.openai.api.impl.audio;

import io.github.jetkai.openai.api.data.audio.AudioData;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * AudioBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.1
 * {@code - 05/03/2023}
 */
public class AudioBuilderImpl implements AudioData.Builder {

    /**
     * file
     * string
     * Required
     * <p>
     * The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     */
    Path file;

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. Only whisper-1 is currently available.
     */
    String model;

    /**
     * prompt
     * string
     * Optional
     * <p>
     * An optional text to guide the model's style or continue a previous audio segment.
     * The prompt should match the audio language.
     */
    List<String> prompt;

    /**
     * response_format
     * string
     * Optional
     * Defaults to json
     * <p>
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     */
    String responseFormat;

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
    double temperature;

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
    String language;


    @Override
    public AudioBuilderImpl setModel(String model) {
        requireNonNull(model);
        this.model = model;
        return this;
    }

    @Override
    public AudioBuilderImpl setPrompt(List<String> prompt) {
        requireNonNull(prompt);
        this.prompt = prompt;
        return this;
    }

    @Override
    public AudioBuilderImpl setPrompt(String prompt) {
        requireNonNull(prompt);
        this.prompt = Collections.singletonList(prompt);
        return this;
    }

    @Override
    public AudioBuilderImpl setResponseFormat(String responseFormat) {
        requireNonNull(responseFormat);
        this.responseFormat = responseFormat;
        return this;
    }

    @Override
    public AudioBuilderImpl setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public AudioBuilderImpl setLanguage(String language) {
        requireNonNull(language);
        this.language = language;
        return this;
    }

    @Override
    public AudioBuilderImpl setFilePath(String filePath) {
        requireNonNull(filePath);
        this.file = Path.of(filePath);
        return this;
    }

    @Override
    public AudioBuilderImpl setFilePath(Path filePath) {
        requireNonNull(filePath);
        this.file = filePath;
        return this;
    }

    @Override
    public AudioData build() {
        return AudioImpl.create(this);
    }
}
