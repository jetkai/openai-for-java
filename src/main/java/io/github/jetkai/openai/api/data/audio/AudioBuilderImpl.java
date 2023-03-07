package io.github.jetkai.openai.api.data.audio;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * AudioBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class AudioBuilderImpl implements AudioData.Builder {

    Path file;
    String model;
    List<String> prompt;
    String responseFormat;
    double temperature;
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
