package io.github.jetkai.openai.api.impl.completion.chat.message;

import io.github.jetkai.openai.api.data.completion.chat.message.ChatCompletionMessageData;

import java.util.Optional;

/**
 * ChatCompletionMessageDataImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public class ChatCompletionMessageDataImpl extends ChatCompletionMessageData {

    private final String role;
    private final String content;

    static ChatCompletionMessageDataImpl create(ChatCompletionMessageDataBuilderImpl builder) {
        return new ChatCompletionMessageDataImpl(builder);
    }

    private ChatCompletionMessageDataImpl(ChatCompletionMessageDataBuilderImpl builder) {
        this.role = builder.role;
        this.content = builder.content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public Optional<String> content() {
        return Optional.ofNullable(this.content);
    }

    @Override
    public Optional<String> role() {
        return Optional.ofNullable(this.role);
    }

}