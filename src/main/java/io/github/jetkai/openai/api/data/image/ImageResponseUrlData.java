package io.github.jetkai.openai.api.data.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * ImageResponseUrlData
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