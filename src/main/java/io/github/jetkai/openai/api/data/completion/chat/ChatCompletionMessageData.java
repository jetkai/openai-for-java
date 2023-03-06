package io.github.jetkai.openai.api.data.completion.chat;

import io.github.jetkai.openai.api.impl.chat.ChatCompletionMessageDataBuilderImpl;

import java.util.Optional;

/**
 * ChatCompletionMessageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 06/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public abstract class ChatCompletionMessageData {

    public ChatCompletionMessageData() { }

    public static ChatCompletionMessageData newAudioData() {
        return builder().build();
    }

    public static ChatCompletionMessageData create() {
        return builder().build();
    }
    public static ChatCompletionMessageData.Builder builder() {
        return new ChatCompletionMessageDataBuilderImpl();
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

    public abstract String getContent();
    public abstract String getRole();

    public abstract Optional<String> content();
    public abstract Optional<String> role();

}