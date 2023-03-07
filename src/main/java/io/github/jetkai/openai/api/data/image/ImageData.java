package io.github.jetkai.openai.api.data.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

/**
 * ImageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class ImageData {

    public ImageData() { }

    public static ImageData.Builder builder() {
        return new ImageBuilderImpl();
    }

    public static ImageData create() {
        return builder().build();
    }

    public interface Builder {
        Builder setUser(String user);
        Builder setN(int n);
        Builder setPrompt(String prompt);
        Builder setResponseFormat(String responseFormat);
        Builder setSize(String size);
        ImageData build();
    }

   /* public static ImageData create() {
        return new ImageData();
    }
    public static ImageData create(String imageDescription) {
        return new ImageData(imageDescription);
    }

    public static ImageData create(String imageDescription, int numberOfImages) {
        return new ImageData(imageDescription, numberOfImages);
    }

    public static ImageData create(String imageDescription, int numberOfImages, String size) {
        return new ImageData(imageDescription, numberOfImages, size);
    }*/

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn More.</a>
     */
    @JsonProperty("user")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public abstract String getUser();

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * The number of images to generate. Must be between 1 and 10.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("n")
    public abstract int getN();

    /**
     * prompt
     * string
     * Required
     * <p>
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    @JsonProperty("prompt")
    public abstract String getPrompt();

    /**
     * response_format
     * string
     * Optional
     * Defaults to url
     * <p>
     * The format in which the generated images are returned. Must be one of url or b64_json.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("response_format")
    public abstract String getResponseFormat();

    /**
     * size
     * string
     * Optional
     * Defaults to 1024x1024
     * <p>
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("size")
    public abstract String getSize();

    public abstract Optional<String> user();
    public abstract Optional<Integer> n();
    public abstract Optional<String> prompt();
    public abstract Optional<String> responseFormat();
    public abstract Optional<String> size();

}
