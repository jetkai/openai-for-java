package io.github.jetkai.openai.api.data.audio.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Optional;

/**
 * AudioResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = AudioResponseData.Builder.class)
public abstract class AudioResponseData {

    public AudioResponseData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("text")
        Builder setText(String text);
        AudioResponseData build();

        @JsonCreator
        static Builder create() {
            return new AudioResponseBuilderImpl();
        }
    }

    public abstract String getText();

    public abstract Optional<String> text();

}
