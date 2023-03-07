package io.github.jetkai.openai.api.data.image.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.image.response.url.ImageResponseUrlData;

import java.util.List;
import java.util.Optional;

/**
 * ImageResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = ImageResponseData.Builder.class)
public abstract class ImageResponseData {

    public ImageResponseData() { }

    public static ImageResponseData.Builder builder() {
        return new ImageResponseBuilderImpl();
    }

    public static ImageResponseData create() {
        return builder().build();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("data")
        Builder setData(List<ImageResponseUrlData> data);
        @JsonProperty("created")
        Builder setCreated(int created);
        ImageResponseData build();

        @JsonCreator
        static Builder create() {
            return new ImageResponseBuilderImpl();
        }

    }

    public abstract int getCreated();
    public abstract List<ImageResponseUrlData> getData();

    public abstract Optional<Integer> created();
    public abstract Optional<List<ImageResponseUrlData>> data();

}
