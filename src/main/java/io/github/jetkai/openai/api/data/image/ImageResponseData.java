package io.github.jetkai.openai.api.data.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.List;

/**
 * ImageResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
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
