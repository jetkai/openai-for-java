package io.github.jetkai.openai.api.data.completion.message;

import java.util.Optional;

/**
 * CompletionMessageImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
final class CompletionMessageImpl extends CompletionMessageData {

    private final String role;
    private final String content;

    static CompletionMessageImpl create(CompletionMessageBuilderImpl builder) {
        return new CompletionMessageImpl(builder);
    }

    private CompletionMessageImpl(CompletionMessageBuilderImpl builder) {
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