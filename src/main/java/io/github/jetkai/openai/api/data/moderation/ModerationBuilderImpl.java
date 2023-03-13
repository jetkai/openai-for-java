package io.github.jetkai.openai.api.data.moderation;

import static java.util.Objects.requireNonNull;

/**
 * ModerationBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
final class ModerationBuilderImpl implements ModerationData.Builder {

    String input;
    String model;

    @Override
    public ModerationData.Builder setInput(String input) {
        requireNonNull(input, "\"input\" can not be null");
        this.input = input;
        return this;
    }

    @Override
    public ModerationData.Builder setModel(String model) {
        requireNonNull(model, "\"model\" can not be null");
        this.model = model;
        return this;
    }

    @Override
    public ModerationData build() {
        return ModerationImpl.create(this);
    }

}
