package io.github.jetkai.openai.api;

import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.api.data.models.ModelsResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListModels
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class ListModels extends OAPI {

    /**
     * ListModels
     */
    public ListModels() {
        super(null, OpenAIEndpoints.LIST_MODELS, HttpRequestType.GET);
    }

    /**
     * asDataArray
     * @return - ModelData[] which contains all the available OpenAI models
     */
    //@Override
    @SuppressWarnings("unused")
    public ModelData[] asDataArray() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelsResponseData.class);
        }
        if (!(this.deserializedData instanceof ModelsResponseData)) {
            return null;
        }
        return ((ModelsResponseData) this.deserializedData).getData();
    }

    /**
     * asDataList
     * @return - {@code List<ModelData>} which contains all the available OpenAI models
     */
    //@Override
    @SuppressWarnings("unused")
    public List<ModelData> asDataList() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelsResponseData.class);
        }
        if (!(this.deserializedData instanceof ModelsResponseData)) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(((ModelsResponseData) this.deserializedData).getData()));
    }


    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModelsResponseData.class);
        }
        if (!(this.deserializedData instanceof ModelsResponseData)) {
            return null;
        }
        return ((ModelsResponseData) this.deserializedData).toJson();
    }

}
