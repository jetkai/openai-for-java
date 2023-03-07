package io.github.jetkai.openai.api.impl.completion.choice;

import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;

import static java.util.Objects.requireNonNull;

/**
 * CompletionChoiceDataBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */

public class CompletionChoiceDataBuilderImpl implements CompletionChoiceData.Builder {
    String id;
    String object;
    int created;
    CompletionMessageData message;
    String text;
    int index;
    String logprobs;
    String finishReason;

    public CompletionChoiceDataBuilderImpl() { }

    @Override
    public CompletionChoiceData.Builder setId(String id) {
        requireNonNull(id);
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
        requireNonNull(object);
        this.object = object;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setMessage(CompletionMessageData message) {
        requireNonNull(message);
        this.message = message;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setLogprobs(String logprobs) {
        requireNonNull(logprobs);
        this.logprobs = logprobs;
        return this;
    }

    @Override
    public CompletionChoiceData.Builder setFinishReason(String finishReason) {
        requireNonNull(finishReason);
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
        requireNonNull(text);
        this.text = text;
        return this;
    }
    @Override
    public CompletionChoiceData build() {
        return CompletionChoiceDataImpl.create(this);
    }

}