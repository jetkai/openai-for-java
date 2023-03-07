package io.github.jetkai.openai.api.impl.completion.message;

import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;

import static java.util.Objects.requireNonNull;

/**
 * CompletionMessageDataBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
public class CompletionMessageDataBuilderImpl implements CompletionMessageData.Builder {

    String role;
    String content;

    @Override
    public CompletionMessageData.Builder setContent(String content) {
        requireNonNull(content);
        this.content = content;
        return this;
    }

    @Override
    public CompletionMessageData.Builder setRole(String role) {
        requireNonNull(role);
        this.role = role;
        return this;
    }

    @Override
    public CompletionMessageData build() {
        return CompletionMessageDataImpl.create(this);
    }

}