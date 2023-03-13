package io.github.jetkai.openai.api.data.moderation;

import java.util.Optional;

/**
 * ModerationImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationImpl extends ModerationData {

    private final String input;
    private final String model;

    static ModerationImpl create(ModerationBuilderImpl builder) {
        return new ModerationImpl(builder);
    }

    private ModerationImpl(ModerationBuilderImpl builder) {
        this.input = builder.input;
        this.model = builder.model;
    }

    @Override
    public String getInput() {
        return this.input;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public Optional<String> input() {
        return Optional.ofNullable(this.input);
    }

    @Override
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

}
