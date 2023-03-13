package io.github.jetkai.openai.api.data.moderation.response.results.scores;

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
final class ModerationScoresImpl extends ModerationScoresData {

    private final float hate;
    private final float hateThreatening;
    private final float selfHarm;
    private final float sexual;
    private final float sexualMinors;
    private final float violence;
    private final float violenceGraphic;

    static ModerationScoresImpl create(ModerationScoresBuilderImpl builder) {
        return new ModerationScoresImpl(builder);
    }

    private ModerationScoresImpl(ModerationScoresBuilderImpl builder) {
        this.hate = builder.hate;
        this.hateThreatening = builder.hateThreatening;
        this.selfHarm = builder.selfHarm;
        this.sexual = builder.sexual;
        this.sexualMinors = builder.sexualMinors;
        this.violence = builder.violence;
        this.violenceGraphic = builder.violenceGraphic;
    }

    @Override
    public float isHate() {
        return this.hate;
    }

    @Override
    public float isHateThreatening() {
        return this.hateThreatening;
    }

    @Override
    public float isSelfHarm() {
        return this.selfHarm;
    }

    @Override
    public float isSexual() {
        return this.sexual;
    }

    @Override
    public float isSexualMinor() {
        return this.sexualMinors;
    }

    @Override
    public float isViolence() {
        return this.violence;
    }

    @Override
    public float isViolenceGraphic() {
        return this.violenceGraphic;
    }

    @Override
    public Optional<Float> hate() {
        return Optional.of(this.hate);
    }

    @Override
    public Optional<Float> hateThreatening() {
        return Optional.of(this.hateThreatening);
    }

    @Override
    public Optional<Float> selfHarm() {
        return Optional.of(this.selfHarm);
    }

    @Override
    public Optional<Float> sexual() {
        return Optional.of(this.sexual);
    }

    @Override
    public Optional<Float> sexualMinors() {
        return Optional.of(this.sexualMinors);
    }

    @Override
    public Optional<Float> violence() {
        return Optional.of(this.violence);
    }

    @Override
    public Optional<Float> violenceGraphic() {
        return Optional.of(this.violenceGraphic);
    }

}
