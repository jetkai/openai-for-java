package io.github.jetkai.openai.api.data.image.variation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.nio.file.Path;

/**
 * ImageVariationData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ImageVariationData {

    /**
     * image
     * string
     * Required
     * <p>
     * The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square.
     */
    private Path image;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * The number of images to generate. Must be between 1 and 10.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int n;

    /**
     * size
     * string
     * Optional
     * Defaults to 1024x1024
     * <p>
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String size;

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
    private String responseFormat;

    /**
     * user
     * string
     * Optional
     *  <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String user;

    public ImageVariationData() { }

    public ImageVariationData setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
        return this;
    }

    public ImageVariationData setImage(String image) {
        this.image = Path.of(URI.create(image));
        return this;
    }

    public ImageVariationData setImage(Path image) {
        this.image = image;
        return this;
    }

    public ImageVariationData setN(int n) {
        this.n = n;
        return this;
    }

    public ImageVariationData setUser(String user) {
        this.user = user;
        return this;
    }

    public ImageVariationData setSize(String size) {
        this.size = size;
        return this;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public String getUser() {
        return user;
    }

    public Path getImage() {
        return image;
    }

    public int getN() {
        return n;
    }

    public String getSize() {
        return size;
    }
}
