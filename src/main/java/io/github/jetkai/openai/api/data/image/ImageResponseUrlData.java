package io.github.jetkai.openai.api.data.image;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ImageResponseUrlData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
@SuppressWarnings("unused")
public class ImageResponseUrlData {

    private String url;

    public ImageResponseUrlData() { }

    public ImageResponseUrlData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

}