package io.github.jetkai.openai.api.data.moderation.response.results.categories;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

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
@JsonDeserialize(builder = ModerationCategoriesData.Builder.class)
public abstract class ModerationCategoriesData {

    public ModerationCategoriesData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("hate")
        Builder setHate(boolean hate);
        @JsonProperty("hate/threatening")
        Builder setHateThreatening(boolean hateThreatening);
        @JsonProperty("self-harm")
        Builder setSelfHarm(boolean selfHarm);
        @JsonProperty("sexual")
        Builder setSexual(boolean sexual);
        @JsonProperty("sexual/minors")
        Builder setSexualMinors(boolean sexualMinors);
        @JsonProperty("violence")
        Builder setViolence(boolean violence);
        @JsonProperty("violence/graphic")
        Builder setViolenceGraphic(boolean violenceGraphic);

        ModerationCategoriesData build();

        @JsonCreator
        static Builder create() {
            return new ModerationCategoriesBuilderImpl();
        }

    }

    public abstract boolean isHate();
    public abstract boolean isHateThreatening();
    public abstract boolean isSelfHarm();
    public abstract boolean isSexual();
    public abstract boolean isSexualMinor();
    public abstract boolean isViolence();
    public abstract boolean isViolenceGraphic();

    public abstract Optional<Boolean> hate();
    public abstract Optional<Boolean> hateThreatening();
    public abstract Optional<Boolean> selfHarm();
    public abstract Optional<Boolean> sexual();
    public abstract Optional<Boolean> sexualMinors();
    public abstract Optional<Boolean> violence();
    public abstract Optional<Boolean> violenceGraphic();

}
