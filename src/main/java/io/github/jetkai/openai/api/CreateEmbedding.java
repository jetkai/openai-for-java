package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.embedding.response.EmbeddingResponseData;
import io.github.jetkai.openai.api.data.embedding.response.block.EmbeddingResponseBlockData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.List;

/**
 * CreateEmbedding
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateEmbedding extends OAPI {

    /**
     * CreateEmbedding
     * @param embedding - The embedding data specified
     */
    public CreateEmbedding(EmbeddingData embedding) {
        super(JacksonJsonDeserializer.valuesAsString(embedding), OpenAIEndpoints.CREATE_EMBEDDING, HttpRequestType.POST);
    }

    /**
     * asFloatList
     * @return {@code List<Float>}
     */
    public List<Float> asFloatList() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(EmbeddingResponseData.class);
        }
        if (!(this.deserializedData instanceof EmbeddingResponseData)) {
            return null;
        }
        List<EmbeddingResponseBlockData> block = ((EmbeddingResponseData) this.deserializedData).getData();
        List<Float> embeddingFloat = null;
        if(block != null && !block.isEmpty()) {
            embeddingFloat = block.get(0).getEmbedding();
        }
        return embeddingFloat;
    }


    /**
     * asData
     * @return EmbeddingResponseData
     */
    public EmbeddingResponseData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(EmbeddingResponseData.class);
        }
        if (!(this.deserializedData instanceof EmbeddingResponseData)) {
            return null;
        }
        return (EmbeddingResponseData) this.deserializedData;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(EmbeddingResponseData.class);
        }
        if (!(this.deserializedData instanceof EmbeddingResponseData)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString((EmbeddingResponseData) this.deserializedData);
    }

}
