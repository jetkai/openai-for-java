package io.github.jetkai.openai.api.data.image.variation;

import java.nio.file.Path;
import java.util.Optional;

/**
 * ImageVariationImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
final class ImageVariationImpl extends ImageVariationData {

    private final Path image;
    private final int n;
    private final String size;
    private final String responseFormat;
    private final String user;

    static ImageVariationImpl create(ImageVariationBuilderImpl builder) {
        return new ImageVariationImpl(builder);
    }

    private ImageVariationImpl(ImageVariationBuilderImpl builder) {
        this.image = builder.image;
        this.n = builder.n;
        this.size = builder.size;
        this.responseFormat = builder.responseFormat;
        this.user = builder.user;
    }

    @Override
    public String getResponseFormat() {
        return this.responseFormat;
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
    public int getN() {
        return this.n;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    @Override
    public Optional<String> responseFormat() {
        return Optional.ofNullable(this.responseFormat);
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
    public Optional<Integer> n() {
        return Optional.of(this.n);
    }

    @Override
    public Optional<String> size() {
        return Optional.ofNullable(this.size);
    }
}
