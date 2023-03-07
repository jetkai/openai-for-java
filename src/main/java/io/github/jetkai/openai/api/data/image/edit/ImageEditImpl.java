package io.github.jetkai.openai.api.data.image.edit;

import java.nio.file.Path;
import java.util.Optional;

/**
 * ImageEditImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class ImageEditImpl extends ImageEditData {

    private final Path image;
    private final Path mask;
    private final String prompt;
    private final int n;
    private final String size;
    private final String responseFormat;
    private final String user;

    static ImageEditImpl create(ImageEditBuilderImpl builder) {
        return new ImageEditImpl(builder);
    }

    private ImageEditImpl(ImageEditBuilderImpl builder) {
        this.prompt = builder.prompt;
        this.n = builder.n;
        this.size = builder.size;
        this.responseFormat = builder.responseFormat;
        this.user = builder.user;
        this.image = builder.image;
        this.mask = builder.mask;
    }

    @Override
    public String getResponseFormat() {
        return this.responseFormat;
    }

    @Override
    public String getPrompt() {
        return this.prompt;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public Path getImage() {
        return this.image;
    }

    @Override
    public Path getMask() {
        return this.mask;
    }

    @Override
    public Optional<String> responseFormat() {
        return Optional.ofNullable(this.responseFormat);
    }

    @Override
    public Optional<String> prompt() {
        return Optional.ofNullable(this.prompt);
    }

    @Override
    public Optional<String> size() {
        return Optional.ofNullable(this.size);
    }

    @Override
    public Optional<Integer> n() {
        return Optional.of(this.n);
    }

    @Override
    public Optional<String> user() {
        return Optional.ofNullable(this.user);
    }

    @Override
    public Optional<Path> image() {
        return Optional.ofNullable(this.image);
    }

    @Override
    public Optional<Path> mask() {
        return Optional.ofNullable(this.mask);
    }
}
