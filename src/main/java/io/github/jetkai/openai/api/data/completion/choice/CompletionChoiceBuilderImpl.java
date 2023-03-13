package io.github.jetkai.openai.api.data.completion.choice;

import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;

import static java.util.Objects.requireNonNull;

/**
 * CompletionChoiceBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */

final class CompletionChoiceBuilderImpl implements CompletionChoiceData.Builder {
    String id;
    String object;
    int created;
    CompletionMessageData message;
    String text;
    int index;
    String logprobs;
    String finishReason;

    public CompletionChoiceBuilderImpl() { }

    @Override
    public CompletionChoiceData.Builder setId(String id) {
        requireNonNull(id, "\"id\" can not be null");
        this.id = id;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setCreated(int created) {
        this.created = created;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setObject(String object) {
        requireNonNull(object, "\"object\" can not be null");
        this.object = object;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setMessage(CompletionMessageData message) {
        requireNonNull(message, "\"message\" can not be null");
        this.message = message;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setLogprobs(String logprobs) {
        requireNonNull(logprobs, "\"logprobs\" can not be null");
        this.logprobs = logprobs;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setFinishReason(String finishReason) {
        requireNonNull(finishReason, "\"finishReason\" can not be null");
        this.finishReason = finishReason;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setText(String text) {
        requireNonNull(text, "\"text\" can not be null");
        this.text = text;
        return this;
    }
    @Override
    public CompletionChoiceData build() {
        return CompletionChoiceImpl.create(this);
    }

}