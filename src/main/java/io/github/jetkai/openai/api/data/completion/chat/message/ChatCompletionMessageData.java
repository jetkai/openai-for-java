package io.github.jetkai.openai.api.data.completion.chat.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

/**
 * ChatCompletionMessageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class ChatCompletionMessageData {

    public ChatCompletionMessageData() { }

    public static ChatCompletionMessageData.Builder builder() {
        return new ChatCompletionMessageBuilderImpl();
    }

    public static ChatCompletionMessageData create(String role, String content) {
        return ChatCompletionMessageData.builder()
                .setRole(role)
                .setContent(content)
                .build();
    }

    public static ChatCompletionMessageData create(String content) {
        return ChatCompletionMessageData.builder()
                .setRole("user")
                .setContent(content)
                .build();
    }

    public interface Builder {
        Builder setContent(String content);
        Builder setRole(String role);
        ChatCompletionMessageData build();
    }

    @JsonProperty("content")
    public abstract String getContent();

    @JsonProperty("role")
    public abstract String getRole();

    public abstract Optional<String> content();
    public abstract Optional<String> role();

}