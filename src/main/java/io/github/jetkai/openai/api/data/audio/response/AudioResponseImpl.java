package io.github.jetkai.openai.api.data.audio.response;


import java.util.Optional;

/**
 * AudioResponseImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class AudioResponseImpl extends AudioResponseData {

    private final String text;


    private AudioResponseImpl(AudioResponseBuilderImpl builder) {
        this.text = builder.text;
    }

    public static AudioResponseImpl create(AudioResponseBuilderImpl builder) {
        return new AudioResponseImpl(builder);
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Optional<String> text() {
        return Optional.ofNullable(this.text);
    }

}
