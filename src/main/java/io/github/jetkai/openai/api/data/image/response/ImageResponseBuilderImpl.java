package io.github.jetkai.openai.api.data.image.response;

import io.github.jetkai.openai.api.data.image.response.url.ImageResponseUrlData;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * ImageResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageResponseBuilderImpl implements ImageResponseData.Builder {

    int created;
    List<ImageResponseUrlData> data;

    @Override
    public ImageResponseData.Builder setData(List<ImageResponseUrlData> data) {
        requireNonNull(data, "\"data\" can not be null");
        this.data = data;
        return this;
    }

    @Override
    public ImageResponseData.Builder setCreated(int created) {
        this.created = created;
        return this;
    }

    @Override
    public ImageResponseData build() {
        return ImageResponseImpl.create(this);
    }

}
