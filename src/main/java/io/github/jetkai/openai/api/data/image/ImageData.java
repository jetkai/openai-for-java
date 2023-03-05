package io.github.jetkai.openai.api.data.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * ImageData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ImageData {

    /**
     * prompt
     * string
     * Required
     * <p>
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    private String prompt;

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
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
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
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn More.</a>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String user;

    public ImageData() { }

    public ImageData(String imageDescription) {
        this.prompt = imageDescription;
    }

    public ImageData(String imageDescription, int numberOfImages) {
        this.prompt = imageDescription;
        this.n = numberOfImages;
    }

    public ImageData(String imageDescription, int numberOfImages, String size) {
        this.prompt = imageDescription;
        this.n = numberOfImages;
        this.size = size;
    }

    public static ImageData create() {
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
    }

    public ImageData setUser(String user) {
        this.user = user;
        return this;
    }

    public ImageData setN(int n) {
        this.n = n;
        return this;
    }

    public ImageData setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public ImageData setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
        return this;
    }

    public ImageData setSize(String size) {
        this.size = size;
        return this;
    }

    public String getUser() {
        return user;
    }

    public int getN() {
        return n;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public String getSize() {
        return size;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
