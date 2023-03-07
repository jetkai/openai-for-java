package io.github.jetkai.openai.api.data.embedding.response.block;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
import java.util.Optional;

/**
 * EmbeddingResponseBlockData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = EmbeddingResponseBlockData.Builder.class)
public abstract class EmbeddingResponseBlockData {

    public EmbeddingResponseBlockData() { }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("object")
        Builder setObject(String object);

        @JsonProperty("index")
        Builder setIndex(int index);

        @JsonProperty("embedding")
        Builder setEmbedding(List<Float> embedding);

        EmbeddingResponseBlockData build();

        @JsonCreator
        static Builder create() {
            return new EmbeddingResponseBlockBuilderImpl();
        }

    }

    public abstract String getObject();
    public abstract int getIndex();
    public abstract List<Float> getEmbedding();

    public abstract Optional<String> object();
    public abstract Optional<Integer> index();
    public abstract Optional<List<Float>> embedding();

}