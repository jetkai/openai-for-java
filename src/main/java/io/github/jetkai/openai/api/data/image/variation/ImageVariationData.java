package io.github.jetkai.openai.api.data.image.variation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.nio.file.Path;
import java.util.Optional;

/**
 * ImageVariationData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class ImageVariationData {

    public ImageVariationData() { }

    public static ImageVariationData.Builder builder() {
        return new ImageVariationBuilderImpl();
    }

    public interface Builder {
        Builder setResponseFormat(String responseFormat);
        Builder setImage(String image);
        Builder setImage(Path image);
        Builder setN(int n);
        Builder setUser(String user);
        Builder setSize(String size);
        ImageVariationData build();
    }

    /**
     * response_format
     * string
     * Optional
     * Defaults to url
     * <p>
     * The format in which the generated images are returned. Must be one of url or b64_json.
     * @return getResponseFormat
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("response_format")
    public abstract String getResponseFormat();

    /**
     * user
     * string
     * Optional
     *  <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
     * @return getUser
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("user")
    public abstract String getUser();

    /**
     * image
     * string
     * Required
     * <p>
     * The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square.
     * @return getImage
     */
    @JsonProperty("image")
    public abstract Path getImage();

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * The number of images to generate. Must be between 1 and 10.
     * @return getN
     */
    @JsonProperty("n")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public abstract int getN();

    /**
     * size
     * string
     * Optional
     * Defaults to 1024x1024
     * <p>
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
     * @return getSize
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("size")
    public abstract String getSize();

    public abstract Optional<String> responseFormat();
    public abstract Optional<String> user();
    public abstract Optional<Path> image();
    public abstract Optional<Integer> n();
    public abstract Optional<String> size();
}
