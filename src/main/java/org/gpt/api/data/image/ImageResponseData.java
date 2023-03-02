package org.gpt.api.data.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ImageResponseData {

    private int created;
    private List<ImageResponses> data;

    public ImageResponseData() { }

    public void setData(List<ImageResponses> data) {
        this.data = data;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getCreated() {
        return created;
    }

    public List<ImageResponses> getData() {
        return data;
    }

}
