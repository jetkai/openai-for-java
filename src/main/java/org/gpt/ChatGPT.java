package org.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gpt.api.CreateChatCompletion;
import org.gpt.api.CreateCompletion;
import org.gpt.api.GetModel;
import org.gpt.api.GetModels;
import org.gpt.api.data.completion.CompletionData;
import org.gpt.api.data.completion.chat.ChatCompletionData;
import org.gpt.api.data.completion.response.CompletionResponseData;
import org.gpt.api.data.model.ModelData;
import org.gpt.api.data.model.ModelsData;
import org.gpt.net.HttpClientInstance;

public class ChatGPT {

    public final HttpClientInstance httpClientInstance = new HttpClientInstance();
    private String apiKey;
    private String organization;

    public ChatGPT() { }

    public ChatGPT(String apiKey) {
        this.apiKey = apiKey;
    }

    public ChatGPT(String apiKey, String organization) {
        this.apiKey = apiKey;
        this.organization = organization;
    }

    public ModelData[] getModels() {
        GetModels models = new GetModels(this);

        if(models.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ModelsData data;
        try {
            data = mapper.readValue(models.getBody(), ModelsData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data.getData();
    }

    public ModelData getModel(String modelName) {
        GetModel model = new GetModel(this, modelName);

        if(model.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ModelData data;
        try {
            data = mapper.readValue(model.getBody(), ModelData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public CompletionResponseData createCompletion(CompletionData completionData) {
        CreateCompletion completion = new CreateCompletion(this, completionData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        CompletionResponseData data;
        try {
            data = mapper.readValue(completion.getBody(), CompletionResponseData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public CompletionResponseData createChatCompletion(ChatCompletionData completionData) {
        CreateChatCompletion completion = new CreateChatCompletion(this, completionData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        if(completion.getBody().contains("\"type\": \"invalid_request_error\"")
                || completion.getBody().contains("\"message\": \"Unrecognized request argument supplied: messages\"")) {
            System.err.println(completion.getBody());
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        CompletionResponseData data;
        try {
            data = mapper.readValue(completion.getBody(), CompletionResponseData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public HttpClientInstance getHttpClientInstance() {
        return httpClientInstance;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getOrganization() {
        return organization;
    }
}
