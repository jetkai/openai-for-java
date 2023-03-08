package io.github.jetkai.openai.api.data.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.model.ModelData;

import java.util.Optional;

/**
 * ModelsResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = ModelsResponseData.Builder.class)
public abstract class ModelsResponseData {

    public ModelsResponseData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("object")
        Builder setObject(String object);
        @JsonProperty("data")
        Builder setData(ModelData[] data);
        ModelsResponseData build();

        @JsonCreator
        static Builder create() {
            return new ModelsResponseBuilderImpl();
        }

    }

    public abstract String getObject();
    public abstract ModelData[] getData();

    public abstract Optional<String> object();
    public abstract Optional<ModelData[]> data();


}
