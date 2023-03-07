package io.github.jetkai.openai.api.impl.completion.message;

import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;

import java.util.Optional;

/**
 * CompletionMessageDataImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
public class CompletionMessageDataImpl extends CompletionMessageData {

    private final String role;
    private final String content;

    static CompletionMessageDataImpl create(CompletionMessageDataBuilderImpl builder) {
        return new CompletionMessageDataImpl(builder);
    }

    private CompletionMessageDataImpl(CompletionMessageDataBuilderImpl builder) {
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