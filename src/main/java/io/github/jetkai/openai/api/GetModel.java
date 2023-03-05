package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.impl.model.ModelImpl;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * GetModel
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@SuppressWarnings("unused")
public class GetModel extends OAPI {

    /**
     * GetModel
     * @param model - The name of the model you wish to search for
     */
    public GetModel(String model) {
        super();
        this.requestData = model;
        this.endpoint = OpenAIEndpoints.GET_MODEL;
        this.requestType = HttpRequestType.GET;
        this.response = new AtomicReference<>();
    }

    /**
     * asData
     * @return ModelData
     */
    public ModelImpl asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelImpl.class);
        }
        if (!(this.deserializedData instanceof ModelImpl)) {
            return null;
        }
        return (ModelImpl) this.deserializedData;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelImpl.class);
        }
        if (!(this.deserializedData instanceof ModelImpl)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString((ModelImpl) this.deserializedData);
    }

}