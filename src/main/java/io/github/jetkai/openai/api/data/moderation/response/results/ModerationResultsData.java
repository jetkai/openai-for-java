package io.github.jetkai.openai.api.data.moderation.response.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.moderation.response.results.categories.ModerationCategoriesData;
import io.github.jetkai.openai.api.data.moderation.response.results.scores.ModerationScoresData;

import java.util.Optional;

/**
 * ModerationResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
@JsonDeserialize(builder = ModerationResultsData.Builder.class)
public abstract class ModerationResultsData {

    public ModerationResultsData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("categories")
        Builder setCategories(ModerationCategoriesData categories);
        @JsonProperty("category_scores")
        Builder setCategoryScores(ModerationScoresData categoryScores);
        @JsonProperty("flagged")
        Builder setFlagged(boolean flagged);
        ModerationResultsData build();

        @JsonCreator
        static Builder create() {
            return new ModerationResultsBuilderImpl();
        }

    }

    public abstract ModerationCategoriesData getCategories();
    public abstract ModerationScoresData getCategoryScores();
    public abstract boolean isFlagged();

    public abstract Optional<ModerationCategoriesData> categories();
    public abstract Optional<ModerationScoresData> categoryScores();
    public abstract Optional<Boolean> flagged();

}
