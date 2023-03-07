package io.github.jetkai.openai.api.data.image.edit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.nio.file.Path;
import java.util.Optional;

/**
 * ImageEditData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
public abstract class ImageEditData {

    public ImageEditData() { }

    public static ImageEditData.Builder builder() {
        return new ImageEditBuilderImpl();
    }

    public static ImageEditData create() {
        return builder().build();
    }

    public interface Builder {
        Builder setResponseFormat(String responseFormat);

        Builder setSize(String size);

        Builder setPrompt(String prompt);

        Builder setN(int n);

        Builder setUser(String user);

        Builder setImage(String image);

        Builder setImage(Path image);

        Builder setMask(String mask);

        Builder setMask(Path mask);

        ImageEditData build();
    }

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
     * prompt
     * string
     * Required
     * <p>
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    @JsonProperty("prompt")
    public abstract String getPrompt();

    /**
     * size
     * string
     * Optional
     * <p>
     * Defaults to 1024x1024
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
     */
    @JsonProperty("size")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public abstract String getSize();

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
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("user")
    public abstract String getUser();
    /**
     * image
     * string
     * Required
     * <p>
     * The image to edit. Must be a valid PNG file, less than 4MB, and square.
     * If mask is not provided, image must have transparency, which will be used as the mask.
     */
    @JsonProperty("image")
    public abstract Path getImage();

    /**
     * mask
     * string
     * Optional
     * <p>
     * An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where
     * image should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as image.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("mask")
    public abstract Path getMask();

    public abstract Optional<String> responseFormat();
    public abstract Optional<String> prompt();
    public abstract Optional<String> size();
    public abstract Optional<Integer> n();
    public abstract Optional<String> user();
    public abstract Optional<Path> image();
    public abstract Optional<Path> mask();

}
