package io.github.jetkai.openai.api.data.completion.message;

import static java.util.Objects.requireNonNull;

/**
 * CompletionMessageBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class CompletionMessageBuilderImpl implements CompletionMessageData.Builder {

    String role;
    String content;

    @Override
    public CompletionMessageData.Builder setContent(String content) {
        requireNonNull(content, "\"content\" can not be null");
        this.content = content;
        return this;
    }

    @Override
    public CompletionMessageData.Builder setRole(String role) {
        requireNonNull(role, "\"role\" can not be null");
        this.role = role;
        return this;
    }

    @Override
    public CompletionMessageData build() {
        return CompletionMessageImpl.create(this);
    }

}