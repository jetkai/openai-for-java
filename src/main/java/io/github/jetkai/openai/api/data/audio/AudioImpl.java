package io.github.jetkai.openai.api.data.audio;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**
 * AudioImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class AudioImpl extends AudioData {

    private final Path file;
    private final String model;
    private final List<String> prompt;
    private final String responseFormat;
    private final double temperature;
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
    public String getModel() {
        return this.model;
    }

    @Override
    public List<String> getPrompt() {
        return this.prompt;
    }

    @Override
    public String getResponseFormat() {
        return this.responseFormat;
    }

    @Override
    public double getTemperature() {
        return this.temperature;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public Path getFilePath() {
        return this.file;
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
