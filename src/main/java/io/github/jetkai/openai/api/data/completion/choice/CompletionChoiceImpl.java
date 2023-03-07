package io.github.jetkai.openai.api.data.completion.choice;

import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;

import java.util.Optional;

/**
 * CompletionChoiceImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
final class CompletionChoiceImpl extends CompletionChoiceData {

    private final String id;
    private final String object;
    private final int created;
    private final CompletionMessageData message;
    private final String text;
    private final int index;
    private final String logprobs;
    private final String finishReason;

    static CompletionChoiceImpl create(CompletionChoiceBuilderImpl builder) {
        return new CompletionChoiceImpl(builder);
    }

    private CompletionChoiceImpl(CompletionChoiceBuilderImpl builder) {
        this.created = builder.created;
        this.logprobs = builder.logprobs;
        this.id = builder.id;
        this.index = builder.index;
        this.finishReason = builder.finishReason;
        this.message = builder.message;
        this.text = builder.text;
        this.object = builder.object;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getCreated() {
        return this.created;
    }

    @Override
    public String getObject() {
        return this.object;
    }

    @Override
    public CompletionMessageData getMessage() {
        return this.message;
    }

    @Override
    public String getLogprobs() {
        return this.logprobs;
    }

    @Override
    public String getFinishReason() {
        return this.finishReason;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Optional<String> id() {
        return Optional.ofNullable(this.id);
    }

    @Override
    public Optional<Integer> created() {
        return Optional.of(this.created);
    }

    @Override
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<CompletionMessageData> message() {
        return Optional.ofNullable(this.message);
    }

    @Override
    public Optional<String> logprobs() {
        return Optional.ofNullable(this.logprobs);
    }

    @Override
    public Optional<String> finishReason() {
        return Optional.ofNullable(this.finishReason);
    }

    @Override
    public Optional<Integer> index() {
        return Optional.of(this.index);
    }

    @Override
    public Optional<String> text() {
        return Optional.ofNullable(this.text);
    }


}