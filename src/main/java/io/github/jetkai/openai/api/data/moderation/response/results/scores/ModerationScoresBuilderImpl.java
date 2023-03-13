package io.github.jetkai.openai.api.data.moderation.response.results.scores;

/**
 * ModelsResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationScoresBuilderImpl implements ModerationScoresData.Builder {

    float hate;
    float hateThreatening;
    float selfHarm;
    float sexual;
    float sexualMinors;
    float violence;
    float violenceGraphic;

    @Override
    public ModerationScoresData.Builder setHate(float hate) {
        this.hate = hate;
        return this;
    }

    @Override
    public ModerationScoresData.Builder setHateThreatening(float hateThreatening) {
        this.hateThreatening = hateThreatening;
        return this;
    }

    @Override
    public ModerationScoresData.Builder setSelfHarm(float selfHarm) {
        this.selfHarm = selfHarm;
        return this;
    }

    @Override
    public ModerationScoresData.Builder setSexual(float sexual) {
        this.sexual = sexual;
        return this;
    }

    @Override
    public ModerationScoresData.Builder setSexualMinors(float sexualMinors) {
        this.sexualMinors = sexualMinors;
        return this;
    }

    @Override
    public ModerationScoresData.Builder setViolence(float violence) {
        this.violence = violence;
        return this;
    }

    @Override
    public ModerationScoresData.Builder setViolenceGraphic(float violenceGraphic) {
        this.violenceGraphic = violenceGraphic;
        return this;
    }

    @Override
    public ModerationScoresData build() {
        return ModerationScoresImpl.create(this);
    }

}
