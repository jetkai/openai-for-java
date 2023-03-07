package io.github.jetkai.openai.api.data.completion.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.impl.completion.message.CompletionMessageDataBuilderImpl;

import java.util.Optional;

/**
 * CompletionMessageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 06/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = CompletionMessageData.Builder.class)
public abstract class CompletionMessageData {

    public CompletionMessageData() { }
    public static CompletionMessageData create() {
        return builder().build();
    }
    public static CompletionMessageData.Builder builder() {
        return new CompletionMessageDataBuilderImpl();
    }

    public static CompletionMessageData create(String role, String content) {
        return CompletionMessageData.builder()
                .setRole(role)
                .setContent(content)
                .build();
    }

    public static CompletionMessageData create(String content) {
        return CompletionMessageData.builder()
                .setRole("user")
                .setContent(content)
                .build();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("content")
        Builder setContent(String content);
        @JsonProperty("role")
        Builder setRole(String role);
        CompletionMessageData build();

        @JsonCreator
        static Builder create() {
            return new CompletionMessageDataBuilderImpl();
        }
    }

    @JsonProperty("content")
    public abstract String getContent();
    @JsonProperty("role")
    public abstract String getRole();

    public abstract Optional<String> content();
    public abstract Optional<String> role();

}