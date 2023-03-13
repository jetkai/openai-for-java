package io.github.jetkai.openai.api.data.image.variation;

import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

/**
 * ImageVariationBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageVariationBuilderImpl implements ImageVariationData.Builder {

    Path image;
    int n;
    String size;
    String responseFormat;
    String user;

    @Override
    public ImageVariationData.Builder setResponseFormat(String responseFormat) {
        requireNonNull(responseFormat, "\"responseFormat\" can not be null");
        this.responseFormat = responseFormat;
        return this;
    }

    @Override
    public ImageVariationData.Builder setImage(String image) {
        requireNonNull(image, "\"image\" can not be null");
        this.image = Path.of(image);
        return this;
    }

    @Override
    public ImageVariationData.Builder setImage(Path image) {
        requireNonNull(image, "\"image\" can not be null");
        this.image = image;
        return this;
    }

    @Override
    public ImageVariationData.Builder setN(int n) {
        this.n = n;
        return this;
    }

    @Override
    public ImageVariationData.Builder setUser(String user) {
        requireNonNull(user, "\"user\" can not be null");
        this.user = user;
        return this;
    }

    @Override
    public ImageVariationData.Builder setSize(String size) {
        requireNonNull(size, "\"size\" can not be null");
        this.size = size;
        return this;
    }

    @Override
    public ImageVariationData build() {
        return ImageVariationImpl.create(this);
    }
}
