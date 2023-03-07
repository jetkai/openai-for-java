package io.github.jetkai.openai.api.data.image.response.url;

import static java.util.Objects.requireNonNull;

/**
 * ImageResponseUrlBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageResponseUrlBuilderImpl implements ImageResponseUrlData.Builder {

    String url;

    @Override
    public ImageResponseUrlData.Builder setUrl(String url) {
        requireNonNull(url);
        this.url = url;
        return this;
    }

    @Override
    public ImageResponseUrlData build() {
        return ImageResponseUrlImpl.create(this);
    }
}