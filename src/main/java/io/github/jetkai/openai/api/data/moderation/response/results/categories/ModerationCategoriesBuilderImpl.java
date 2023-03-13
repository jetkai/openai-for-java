package io.github.jetkai.openai.api.data.moderation.response.results.categories;

/**
 * ModelsResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationCategoriesBuilderImpl implements ModerationCategoriesData.Builder {

    boolean hate;
    boolean hateThreatening;
    boolean selfHarm;
    boolean sexual;
    boolean sexualMinors;
    boolean violence;
    boolean violenceGraphic;

    @Override
    public ModerationCategoriesData.Builder setHate(boolean hate) {
        this.hate = hate;
        return this;
    }

    @Override
    public ModerationCategoriesData.Builder setHateThreatening(boolean hateThreatening) {
        this.hateThreatening = hateThreatening;
        return this;
    }

    @Override
    public ModerationCategoriesData.Builder setSelfHarm(boolean selfHarm) {
        this.selfHarm = selfHarm;
        return this;
    }

    @Override
    public ModerationCategoriesData.Builder setSexual(boolean sexual) {
        this.sexual = sexual;
        return this;
    }

    @Override
    public ModerationCategoriesData.Builder setSexualMinors(boolean sexualMinors) {
        this.sexualMinors = sexualMinors;
        return this;
    }

    @Override
    public ModerationCategoriesData.Builder setViolence(boolean violence) {
        this.violence = violence;
        return this;
    }

    @Override
    public ModerationCategoriesData.Builder setViolenceGraphic(boolean violenceGraphic) {
        this.violenceGraphic = violenceGraphic;
        return this;
    }

    @Override
    public ModerationCategoriesData build() {
        return ModerationCategoriesImpl.create(this);
    }
}
