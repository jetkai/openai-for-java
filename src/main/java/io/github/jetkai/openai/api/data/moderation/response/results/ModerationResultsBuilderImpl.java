package io.github.jetkai.openai.api.data.moderation.response.results;

import io.github.jetkai.openai.api.data.moderation.response.results.categories.ModerationCategoriesData;
import io.github.jetkai.openai.api.data.moderation.response.results.scores.ModerationScoresData;

import static java.util.Objects.requireNonNull;

/**
 * ModerationResultsBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationResultsBuilderImpl implements ModerationResultsData.Builder {

    ModerationCategoriesData categories;
    ModerationScoresData categoryScores;
    boolean flagged;

    @Override
    public ModerationResultsData.Builder setCategories(ModerationCategoriesData categories) {
        requireNonNull(categories, "\"categories\" can not be null");
        this.categories = categories;
        return this;
    }

    @Override
    public ModerationResultsData.Builder setCategoryScores(ModerationScoresData categoryScores) {
        requireNonNull(categoryScores, "\"categoryScores\" can not be null");
        this.categoryScores = categoryScores;
        return this;
    }

    @Override
    public ModerationResultsData.Builder setFlagged(boolean flagged) {
        this.flagged = flagged;
        return this;
    }

    @Override
    public ModerationResultsData build() {
        return ModerationResultsImpl.create(this);
    }

}
