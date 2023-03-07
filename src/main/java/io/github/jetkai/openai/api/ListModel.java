package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * ListModel
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class ListModel extends OAPI {

    /**
     * ListModel
     * @param model - The name of the model you wish to search for
     */
    public ListModel(String model) {
        super(model, OpenAIEndpoints.LIST_MODELS, HttpRequestType.GET);
    }

    /**
     * asData
     * @return ModelData
     */
    public ModelData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelData.class);
        }
        if (!(this.deserializedData instanceof ModelData)) {
            return null;
        }
        return (ModelData) this.deserializedData;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelData.class);
        }
        if (!(this.deserializedData instanceof ModelData)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString((ModelData) this.deserializedData);
    }

}