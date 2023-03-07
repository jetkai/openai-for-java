package io.github.jetkai.openai.api.data.image;

import java.util.Optional;

/**
 * ImageImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageImpl extends ImageData {

    private final String prompt;
    private final int n;
    private final String size;
    private final String responseFormat;
    private final String user;

    static ImageImpl create(ImageBuilderImpl builder) {
        return new ImageImpl(builder);
    }

    private ImageImpl(ImageBuilderImpl builder) {
        this.prompt = builder.prompt;
        this.n = builder.n;
        this.size = builder.size;
        this.responseFormat = builder.responseFormat;
        this.user = builder.user;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public String getPrompt() {
        return this.prompt;
    }

    @Override
    public String getResponseFormat() {
        return this.responseFormat;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    @Override
    public Optional<String> user() {
        return Optional.ofNullable(this.user);
    }

    @Override
    public Optional<Integer> n() {
        return Optional.of(this.n);
    }

    @Override
    public Optional<String> prompt() {
        return Optional.ofNullable(this.prompt);
    }

    @Override
    public Optional<String> responseFormat() {
        return Optional.ofNullable(this.responseFormat);
    }

    @Override
    public Optional<String> size() {
        return Optional.ofNullable(this.size);
    }

}
