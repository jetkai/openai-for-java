package io.github.jetkai.openai.api.data.completion.response;

import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.usage.CompletionUsageData;

import java.util.List;
import java.util.Optional;

/**
 * CompletionResponseImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 02/03/2023}
 */

final class CompletionResponseImpl extends CompletionResponseData {

    private final String id;
    private final int created;
    private final String object;
    private final String model;
    private final List<CompletionChoiceData> choices;
    private final CompletionUsageData usage;

    private CompletionResponseImpl(CompletionResponseBuilderImpl builder) {
        this.id = builder.id;
        this.object = builder.object;
        this.model = builder.model;
        this.created = builder.created;
        this.choices = builder.choices;
        this.usage = builder.usage;
    }

    public static CompletionResponseImpl create(CompletionResponseBuilderImpl builder) {
        return new CompletionResponseImpl(builder);
    }

    @Override
    public int getCreated() {
        return this.created;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getObject() {
        return this.object;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public List<CompletionChoiceData> getChoices() {
        return this.choices;
    }

    @Override
    public CompletionUsageData getUsage() {
        return this.usage;
    }

    @Override
    public Optional<Integer> created() {
        return Optional.of(this.created);
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<String> object() {
       return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<String> id() {
       return Optional.ofNullable(this.id);
    }

    @Override
    public Optional<List<CompletionChoiceData>> choices() {
        return Optional.ofNullable(this.choices);
    }

    @Override
    public Optional<CompletionUsageData> usage() {
        return Optional.ofNullable(this.usage);
    }

}