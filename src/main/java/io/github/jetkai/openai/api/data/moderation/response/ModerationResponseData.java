package io.github.jetkai.openai.api.data.moderation.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.moderation.response.results.ModerationResultsData;

import java.util.List;
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
@JsonDeserialize(builder = ModerationResponseData.Builder.class)
public abstract class ModerationResponseData {

    public ModerationResponseData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("id")
        Builder setId(String id);
        @JsonProperty("model")
        Builder setModel(String model);
        @JsonProperty("results")
        Builder setResults(List<ModerationResultsData> results);

        ModerationResponseData build();

        @JsonCreator
        static Builder create() {
            return new ModerationResponseBuilderImpl();
        }

    }

    public abstract String getId();
    public abstract String getModel();
    public abstract List<ModerationResultsData> getResults();

    public abstract Optional<String> id();
    public abstract Optional<String> model();
    public abstract Optional<List<ModerationResultsData>> results();

}
