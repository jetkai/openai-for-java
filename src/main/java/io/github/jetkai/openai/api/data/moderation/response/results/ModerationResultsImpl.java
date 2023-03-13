package io.github.jetkai.openai.api.data.moderation.response.results;

import io.github.jetkai.openai.api.data.moderation.response.results.categories.ModerationCategoriesData;
import io.github.jetkai.openai.api.data.moderation.response.results.scores.ModerationScoresData;

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
final class ModerationResultsImpl extends ModerationResultsData {

    private final ModerationCategoriesData categories;
    private final ModerationScoresData categoryScores;
    private final boolean flagged;

    static ModerationResultsImpl create(ModerationResultsBuilderImpl builder) {
        return new ModerationResultsImpl(builder);
    }

    private ModerationResultsImpl(ModerationResultsBuilderImpl builder) {
        this.categories = builder.categories;
        this.categoryScores = builder.categoryScores;
        this.flagged = builder.flagged;
    }

    @Override
    public ModerationCategoriesData getCategories() {
        return this.categories;
    }

    @Override
    public ModerationScoresData getCategoryScores() {
        return this.categoryScores;
    }

    @Override
    public boolean isFlagged() {
        return this.flagged;
    }

    @Override
    public Optional<ModerationCategoriesData> categories() {
        return Optional.ofNullable(this.categories);
    }

    @Override
    public Optional<ModerationScoresData> categoryScores() {
        return Optional.ofNullable(this.categoryScores);
    }

    @Override
    public Optional<Boolean> flagged() {
        return Optional.of(this.flagged);
    }
}
