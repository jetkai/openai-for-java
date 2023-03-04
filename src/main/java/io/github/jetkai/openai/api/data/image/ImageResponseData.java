package io.github.jetkai.openai.api.data.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.List;

/**
 * ImageResponseData
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
public class ImageResponseData {

    private int created;
    private List<ImageResponseUrlData> data;

    public ImageResponseData() { }

    public ImageResponseData setData(List<ImageResponseUrlData> data) {
        this.data = data;
        return this;
    }

    public ImageResponseData setCreated(int created) {
        this.created = created;
        return this;
    }

    public int getCreated() {
        return created;
    }

    public List<ImageResponseUrlData> getData() {
        return data;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
