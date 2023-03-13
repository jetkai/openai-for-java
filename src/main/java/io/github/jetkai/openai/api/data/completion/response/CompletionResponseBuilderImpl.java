package io.github.jetkai.openai.api.data.completion.response;

import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.usage.CompletionUsageData;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * CompletionResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class CompletionResponseBuilderImpl implements CompletionResponseData.Builder {

    String id;
    String object;
    String model;
    List<CompletionChoiceData> choices;
    CompletionUsageData usage;
    int created;

    @Override
    public CompletionResponseData.Builder setCreated(int created) {
        this.created = created;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setModel(String model) {
        requireNonNull(model, "\"model\" can not be null");
        this.model = model;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setObject(String object) {
        requireNonNull(object, "\"object\" can not be null");
        this.object = object;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setId(String id) {
        requireNonNull(id, "\"id\" can not be null");
        this.id = id;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setChoices(List<CompletionChoiceData> choices) {
        requireNonNull(choices, "\"choices\" can not be null");
        this.choices = choices;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setUsage(CompletionUsageData usage) {
        requireNonNull(usage, "\"usage\" can not be null");
        this.usage = usage;
        return this;
    }

    @Override
    public CompletionResponseData build() {
        //return this;
        return CompletionResponseImpl.create(this);
    }

}