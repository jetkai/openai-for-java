package io.github.jetkai.openai.api.data.moderation.response;

import io.github.jetkai.openai.api.data.moderation.response.results.ModerationResultsData;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * ModelsResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationResponseBuilderImpl implements ModerationResponseData.Builder {

    String id;
    String model;
    List<ModerationResultsData> results;

    @Override
    public ModerationResponseData.Builder setId(String id) {
        requireNonNull(id, "\"id\" can not be null");
        this.id = id;
        return this;
    }

    @Override
    public ModerationResponseData.Builder setModel(String model) {
        requireNonNull(model, "\"model\" can not be null");
        this.model = model;
        return this;
    }

    @Override
    public ModerationResponseData.Builder setResults(List<ModerationResultsData> results) {
        requireNonNull(results, "\"results\" can not be null");
        this.results = results;
        return this;
    }

    @Override
    public ModerationResponseData build() {
        return ModerationResponseImpl.create(this);
    }

}
