package io.github.jetkai.openai.api.data.image.response;

import io.github.jetkai.openai.api.data.image.response.url.ImageResponseUrlData;

import java.util.List;
import java.util.Optional;

/**
 * ImageResponseImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageResponseImpl extends ImageResponseData {

    private final int created;
    private final List<ImageResponseUrlData> data;

    static ImageResponseImpl create(ImageResponseBuilderImpl builder) {
        return new ImageResponseImpl(builder);
    }

    private ImageResponseImpl(ImageResponseBuilderImpl builder) {
        this.created = builder.created;
        this.data = builder.data;
    }

    @Override
    public int getCreated() {
        return this.created;
    }

    @Override
    public List<ImageResponseUrlData> getData() {
        return this.data;
    }

    @Override
    public Optional<Integer> created() {
        return Optional.of(this.created);
    }

    @Override
    public Optional<List<ImageResponseUrlData>> data() {
        return Optional.ofNullable(this.data);
    }

}
