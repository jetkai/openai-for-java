package io.github.jetkai.openai.api.data.image.edit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.URI;
import java.nio.file.Path;

/**
 * ImageEditData
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
public class ImageEditData {

    /**
     * image
     * string
     * Required
     * <p>
     * The image to edit. Must be a valid PNG file, less than 4MB, and square.
     * If mask is not provided, image must have transparency, which will be used as the mask.
     */
    private Path image;

    /**
     * mask
     * string
     * Optional
     * <p>
     * An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where
     * image should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as image.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Path mask;

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
     * <p>
     * Defaults to 1024x1024
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
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String user;

    public ImageEditData() { }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setImage(String image) {
        this.image = Path.of(URI.create(image));
    }

    public void setImage(Path image) {
        this.image = image;
    }

    public void setMask(String mask) {
        this.mask = Path.of(URI.create(mask));
    }

    public void setMask(Path mask) {
        this.mask = mask;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getSize() {
        return size;
    }

    public int getN() {
        return n;
    }

    public String getUser() {
        return user;
    }

    public Path getImage() {
        return image;
    }

    public Path getMask() {
        return mask;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
