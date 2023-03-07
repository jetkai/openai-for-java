package io.github.jetkai.openai.api.data.embedding.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.embedding.response.block.EmbeddingResponseBlockData;
import io.github.jetkai.openai.api.data.embedding.response.usage.EmbeddingResponseUsageData;

import java.util.List;
import java.util.Optional;

/**
 * EmbeddingResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = EmbeddingResponseData.Builder.class)
public abstract class EmbeddingResponseData {

    public EmbeddingResponseData() { }

    public static EmbeddingResponseData.Builder builder() {
        return new EmbeddingResponseBuilderImpl();
    }

    public static EmbeddingResponseData create() {
        return builder().build();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("model")
        Builder setModel(String model);
        @JsonProperty("data")
        Builder setData(List<EmbeddingResponseBlockData> data);
        @JsonProperty("object")
        Builder setObject(String object);
        @JsonProperty("usage")
        Builder setUsage(EmbeddingResponseUsageData usage);
        EmbeddingResponseData build();

        @JsonCreator
        static Builder create() {
            return new EmbeddingResponseBuilderImpl();
        }

    }

    public abstract String getModel();
    public abstract String getObject();
    public abstract List<EmbeddingResponseBlockData> getData();
    public abstract EmbeddingResponseUsageData getUsage();

    public abstract Optional<String> model();
    public abstract Optional<String> object();
    public abstract Optional<List<EmbeddingResponseBlockData>> data();
    public abstract Optional<EmbeddingResponseUsageData> usage();

}
