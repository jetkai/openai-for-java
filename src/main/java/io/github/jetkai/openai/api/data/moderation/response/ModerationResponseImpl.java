package io.github.jetkai.openai.api.data.moderation.response;

import io.github.jetkai.openai.api.data.moderation.response.results.ModerationResultsData;

import java.util.List;
import java.util.Optional;

/**
 * ModelsResponseImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationResponseImpl extends ModerationResponseData {

    private final String id;
    private final String model;
    private final List<ModerationResultsData> results;

    static ModerationResponseImpl create(ModerationResponseBuilderImpl builder) {
        return new ModerationResponseImpl(builder);
    }

    private ModerationResponseImpl(ModerationResponseBuilderImpl builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.results = builder.results;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public List<ModerationResultsData> getResults() {
        return this.results;
    }

    @Override
    public Optional<String> id() {
        return Optional.ofNullable(this.id);
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<List<ModerationResultsData>> results() {
        return Optional.ofNullable(this.results);
    }

}
