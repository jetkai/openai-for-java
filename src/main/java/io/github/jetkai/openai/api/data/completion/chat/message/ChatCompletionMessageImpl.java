package io.github.jetkai.openai.api.data.completion.chat.message;

import java.util.Optional;

/**
 * ChatCompletionMessageImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
final class ChatCompletionMessageImpl extends ChatCompletionMessageData {

    private final String role;
    private final String content;

    static ChatCompletionMessageImpl create(ChatCompletionMessageBuilderImpl builder) {
        return new ChatCompletionMessageImpl(builder);
    }

    private ChatCompletionMessageImpl(ChatCompletionMessageBuilderImpl builder) {
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