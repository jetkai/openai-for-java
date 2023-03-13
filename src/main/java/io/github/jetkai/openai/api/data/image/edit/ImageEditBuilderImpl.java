package io.github.jetkai.openai.api.data.image.edit;

import java.nio.file.Path;

import static java.util.Objects.requireNonNull;

/**
 * ImageEditBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageEditBuilderImpl implements ImageEditData.Builder {

    Path image;
    Path mask;
    String prompt;
    int n;
    String size;
    String responseFormat;
    String user;

    @Override
    public ImageEditData.Builder setResponseFormat(String responseFormat) {
        requireNonNull(responseFormat, "\"responseFormat\" can not be null");
        this.responseFormat = responseFormat;
        return this;
    }

    @Override
    public ImageEditData.Builder setSize(String size) {
        requireNonNull(size, "\"size\" can not be null");
        this.size = size;
        return this;
    }

    @Override
    public ImageEditData.Builder setPrompt(String prompt) {
        requireNonNull(prompt, "\"prompt\" can not be null");
        this.prompt = prompt;
        return this;
    }

    @Override
    public ImageEditData.Builder setN(int n) {
        this.n = n;
        return this;
    }

    @Override
    public ImageEditData.Builder setUser(String user) {
        requireNonNull(user, "\"user\" can not be null");
        this.user = user;
        return this;
    }

    @Override
    public ImageEditData.Builder setImage(String image) {
        requireNonNull(image, "\"image\" can not be null");
        this.image = Path.of(image);
        return this;
    }

    @Override
    public ImageEditData.Builder setImage(Path image) {
        requireNonNull(image, "\"image\" can not be null");
        this.image = image;
        return this;
    }

    @Override
    public ImageEditData.Builder setMask(String mask) {
        requireNonNull(mask, "\"mask\" can not be null");
        this.mask = Path.of(mask);
        return this;
    }

    @Override
    public ImageEditData.Builder setMask(Path mask) {
        requireNonNull(mask, "\"mask\" can not be null");
        this.mask = mask;
        return this;
    }

    @Override
    public ImageEditData build() {
        return ImageEditImpl.create(this);
    }

}
