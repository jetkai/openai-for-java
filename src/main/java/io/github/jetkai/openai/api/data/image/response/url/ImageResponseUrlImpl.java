package io.github.jetkai.openai.api.data.image.response.url;

import java.util.Optional;

/**
 * ImageResponseUrlImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageResponseUrlImpl extends ImageResponseUrlData {

    private final String url;

    static ImageResponseUrlImpl create(ImageResponseUrlBuilderImpl builder) {
        return new ImageResponseUrlImpl(builder);
    }

    private ImageResponseUrlImpl(ImageResponseUrlBuilderImpl builder) {
        this.url = builder.url;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public Optional<String> url() {
        return Optional.ofNullable(this.url);
    }

}