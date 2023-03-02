package io.github.jetkai.chatgpt.api.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ModelsData {

    private String object;
    private ModelData[] data;

    public ModelsData() { }

    public ModelsData(String object, ModelData[] data) {
        this.object = object;
        this.data = data;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setData(ModelData[] data) {
        this.data = data;
    }

    public String getObject() {
        return object;
    }

    public ModelData[] getData() {
        return data;
    }

    @JsonIgnore
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}
