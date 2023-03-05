package io.github.jetkai.openai.api.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.api.impl.model.ModelImpl;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * ModelsResponseData
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
public class ModelsResponseData {

    private String object;
    private ModelImpl[] data;

    public ModelsResponseData() { }

    public ModelsResponseData setObject(String object) {
        this.object = object;
        return this;
    }

    public ModelsResponseData setData(ModelImpl[] data) {
        this.data = data;
        return this;
    }

    public String getObject() {
        return object;
    }

    public ModelData[] getData() {
        return data;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
