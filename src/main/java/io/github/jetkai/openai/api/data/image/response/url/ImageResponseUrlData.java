package io.github.jetkai.openai.api.data.image.response.url;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Optional;

/**
 * ImageResponseUrlData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = ImageResponseUrlData.Builder.class)
public abstract class ImageResponseUrlData {

    public ImageResponseUrlData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("url")
        Builder setUrl(String url);
        ImageResponseUrlData build();

        @JsonCreator
        static Builder create() {
            return new ImageResponseUrlBuilderImpl();
        }

    }

    public abstract String getUrl();

    public abstract Optional<String> url();

}