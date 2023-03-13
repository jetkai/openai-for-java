package io.github.jetkai.openai.api.data.moderation.response.results.scores;

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
@JsonDeserialize(builder = ModerationScoresData.Builder.class)
public abstract class ModerationScoresData {

    public ModerationScoresData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("hate")
        Builder setHate(float hate);
        @JsonProperty("hate/threatening")
        Builder setHateThreatening(float hateThreatening);
        @JsonProperty("self-harm")
        Builder setSelfHarm(float selfHarm);
        @JsonProperty("sexual")
        Builder setSexual(float sexual);
        @JsonProperty("sexual/minors")
        Builder setSexualMinors(float sexualMinors);
        @JsonProperty("violence")
        Builder setViolence(float violence);
        @JsonProperty("violence/graphic")
        Builder setViolenceGraphic(float violenceGraphic);
        
        ModerationScoresData build();

        @JsonCreator
        static Builder create() {
            return new ModerationScoresBuilderImpl();
        }

    }

    public abstract float isHate();
    public abstract float isHateThreatening();
    public abstract float isSelfHarm();
    public abstract float isSexual();
    public abstract float isSexualMinor();
    public abstract float isViolence();
    public abstract float isViolenceGraphic();

    public abstract Optional<Float> hate();
    public abstract Optional<Float> hateThreatening();
    public abstract Optional<Float> selfHarm();
    public abstract Optional<Float> sexual();
    public abstract Optional<Float> sexualMinors();
    public abstract Optional<Float> violence();
    public abstract Optional<Float> violenceGraphic();

}
