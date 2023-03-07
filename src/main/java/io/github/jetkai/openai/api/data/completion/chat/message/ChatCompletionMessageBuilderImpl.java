package io.github.jetkai.openai.api.data.completion.chat.message;

import static java.util.Objects.requireNonNull;

/**
 * ChatCompletionMessageBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ChatCompletionMessageBuilderImpl implements ChatCompletionMessageData.Builder {

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
        return ChatCompletionMessageImpl.create(this);
    }

}