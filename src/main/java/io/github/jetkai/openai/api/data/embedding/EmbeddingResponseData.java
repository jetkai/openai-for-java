package io.github.jetkai.openai.api.data.embedding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.List;

/**
 * EmbeddingResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
@SuppressWarnings("unused")
public class EmbeddingResponseData {

    private String object;
    private List<EmbeddingResponseBlockData> data;

    private String model;
    private EmbeddingResponseUsageData usage;

    public EmbeddingResponseData() { }

    public EmbeddingResponseData setModel(String model) {
        this.model = model;
        return this;
    }

    public EmbeddingResponseData setData(List<EmbeddingResponseBlockData> data) {
        this.data = data;
        return this;
    }

    public EmbeddingResponseData setObject(String object) {
        this.object = object;
        return this;
    }

    public EmbeddingResponseData setUsage(EmbeddingResponseUsageData usage) {
        this.usage = usage;
        return this;
    }

    public String getModel() {
        return model;
    }

    public String getObject() {
        return object;
    }

    public List<EmbeddingResponseBlockData> getData() {
        return data;
    }

    public EmbeddingResponseUsageData getUsage() {
        return usage;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}
