package io.github.jetkai.openai.api.impl.completion.response;

import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.api.data.completion.usage.CompletionUsageData;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * CompletionResponseDataBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */
public class CompletionResponseDataBuilderImpl implements CompletionResponseData.Builder {

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
        requireNonNull(model);
        this.model = model;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setObject(String object) {
        requireNonNull(object);
        this.object = object;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setId(String id) {
        requireNonNull(id);
        this.id = id;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setChoices(List<CompletionChoiceData> choices) {
        requireNonNull(choices);
        this.choices = choices;
        return this;
    }

    @Override
    public CompletionResponseData.Builder setUsage(CompletionUsageData usage) {
        requireNonNull(usage);
        this.usage = usage;
        return this;
    }

    @Override
    public CompletionResponseData build() {
        //return this;
        return CompletionResponseDataImpl.create(this);
    }

}