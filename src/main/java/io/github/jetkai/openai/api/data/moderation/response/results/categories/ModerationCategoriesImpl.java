package io.github.jetkai.openai.api.data.moderation.response.results.categories;

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
final class ModerationCategoriesImpl extends ModerationCategoriesData {

    private final boolean hate;
    private final boolean hateThreatening;
    private final boolean selfHarm;
    private final boolean sexual;
    private final boolean sexualMinors;
    private final boolean violence;
    private final boolean violenceGraphic;

    static ModerationCategoriesImpl create(ModerationCategoriesBuilderImpl builder) {
        return new ModerationCategoriesImpl(builder);
    }

    private ModerationCategoriesImpl(ModerationCategoriesBuilderImpl builder) {
        this.hate = builder.hate;
        this.hateThreatening = builder.hateThreatening;
        this.selfHarm = builder.selfHarm;
        this.sexual = builder.sexual;
        this.sexualMinors = builder.sexualMinors;
        this.violence = builder.violence;
        this.violenceGraphic = builder.violenceGraphic;
    }

    @Override
    public boolean isHate() {
        return this.hate;
    }

    @Override
    public boolean isHateThreatening() {
        return this.hateThreatening;
    }

    @Override
    public boolean isSelfHarm() {
        return this.selfHarm;
    }

    @Override
    public boolean isSexual() {
        return this.sexual;
    }

    @Override
    public boolean isSexualMinor() {
        return this.sexualMinors;
    }

    @Override
    public boolean isViolence() {
        return this.violence;
    }

    @Override
    public boolean isViolenceGraphic() {
        return this.violenceGraphic;
    }

    @Override
    public Optional<Boolean> hate() {
        return Optional.of(this.hate);
    }

    @Override
    public Optional<Boolean> hateThreatening() {
        return Optional.of(this.hateThreatening);
    }

    @Override
    public Optional<Boolean> selfHarm() {
        return Optional.of(this.selfHarm);
    }

    @Override
    public Optional<Boolean> sexual() {
        return Optional.of(this.sexual);
    }

    @Override
    public Optional<Boolean> sexualMinors() {
        return Optional.of(this.sexualMinors);
    }

    @Override
    public Optional<Boolean> violence() {
        return Optional.of(this.violence);
    }

    @Override
    public Optional<Boolean> violenceGraphic() {
        return Optional.of(this.violenceGraphic);
    }

}
