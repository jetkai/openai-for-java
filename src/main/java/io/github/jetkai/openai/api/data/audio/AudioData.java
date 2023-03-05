package io.github.jetkai.openai.api.data.audio;


import io.github.jetkai.openai.api.impl.audio.AudioBuilderImpl;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * AudioData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public abstract class AudioData {

    public AudioData() { }

    public static AudioData newAudioData() {
        return builder().build();
    }

    public static AudioData.Builder builder() {
        return new AudioBuilderImpl();
    }

    public static AudioData create() {
        return builder().build();
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

    public abstract Optional<String> model();
    public abstract Optional<List<String>> prompt();
    public abstract Optional<String> responseFormat();
    public abstract Optional<Double> temperature();
    public abstract Optional<String> language();
    public abstract Optional<Path> filePath();

}
