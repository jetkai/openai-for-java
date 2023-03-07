package io.github.jetkai.openai.api.impl.completion.chat.message;

import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;

import static java.util.Objects.requireNonNull;

/**
 * ChatCompletionMessageDataBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
public class ChatCompletionMessageDataBuilderImpl implements ChatCompletionMessageData.Builder {

    String role;
    String content;

    @Override
    public ChatCompletionMessageData.Builder setContent(String content) {
        requireNonNull(content);
        this.content = content;
        return this;
    }

    @Override
    public ChatCompletionMessageData.Builder setRole(String role) {
        requireNonNull(role);
        this.role = role;
        return this;
    }

    @Override
    public ChatCompletionMessageData build() {
        return ChatCompletionMessageDataImpl.create(this);
    }

}