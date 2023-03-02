package org.gpt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gpt.api.*;
import org.gpt.api.data.completion.CompletionData;
import org.gpt.api.data.completion.chat.ChatCompletionData;
import org.gpt.api.data.completion.response.CompletionChoice;
import org.gpt.api.data.completion.response.CompletionMessage;
import org.gpt.api.data.completion.response.CompletionResponseData;
import org.gpt.api.data.edit.EditData;
import org.gpt.api.data.image.ImageData;
import org.gpt.api.data.image.ImageResponseData;
import org.gpt.api.data.image.ImageResponses;
import org.gpt.api.data.model.ModelData;
import org.gpt.api.data.model.ModelsData;
import org.gpt.net.HttpClientInstance;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

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

    public String[] createChatCompletion(ChatCompletionData completionData) {
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

        String[] response = new String[data.getChoices().size()];

        for(int i = 0; i < data.getChoices().size(); i++) {
            CompletionChoice choice = data.getChoices().get(i);
            if(choice == null) {
                continue;
            }
            CompletionMessage message = choice.getMessage();
            if(message == null) {
                continue;
            }
            String content = message.getContent();
            if(content == null || content.isEmpty()) {
                continue;
            }
            response[i] = content;
        }

        return response;
    }

    public CompletionResponseData createChatCompletionResponse(ChatCompletionData completionData) {
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

    @SuppressWarnings("unused")
    public String[] createEdit(EditData editData) {

        CreateEdit completion = new CreateEdit(this, editData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        if(completion.getBody().contains("\"type\": \"invalid_request_error\"")) {
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
        String[] choicesText = new String[data.getChoices().size()];

        for(int i = 0; i < data.getChoices().size(); i++) {
            CompletionChoice completionChoice = data.getChoices().get(i);
            if(completionChoice == null) {
                continue;
            }
            String choiceText = completionChoice.getText();
            if(choiceText == null || choiceText.isEmpty()) {
                continue;
            }
            choicesText[i] = choiceText;
        }

        return choicesText;
    }

    public CompletionResponseData createEditResponse(EditData editData) {
        CreateEdit completion = new CreateEdit(this, editData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        if(completion.getBody().contains("\"type\": \"invalid_request_error\"")) {
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

    public Image[] createImage(ImageData imageData) {
        CreateImage completion = new CreateImage(this, imageData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        if(completion.getBody().contains("\"type\": \"invalid_request_error\"")) {
            System.err.println(completion.getBody());
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ImageResponseData data;
        try {
            data = mapper.readValue(completion.getBody(), ImageResponseData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(data == null || data.getData() == null || data.getData().isEmpty()) {
            System.err.println("Unable to create image.");
            return null;
        }

        Image[] images = new Image[data.getData().size()];

        for(int i = 0; i < data.getData().size(); i++) {
            ImageResponses imageResponse = data.getData().get(i);
            if(imageResponse == null) {
                continue;
            }
            String imageResponseUrl = imageResponse.getUrl();
            if(imageResponseUrl == null || imageResponseUrl.isEmpty()) {
                continue;
            }
            URL imageUrl;
            try {
                imageUrl = new URL(imageResponseUrl);
                images[i] = ImageIO.read(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return images;
    }

    public ImageResponseData createImageResponse(ImageData imageData) {
        CreateImage completion = new CreateImage(this, imageData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        if(completion.getBody().contains("\"type\": \"invalid_request_error\"")) {
            System.err.println(completion.getBody());
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ImageResponseData data;
        try {
            data = mapper.readValue(completion.getBody(), ImageResponseData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public String[] createImageUrls(ImageData imageData) {
        CreateImage completion = new CreateImage(this, imageData);

        if(completion.getBody().contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        if(completion.getBody().contains("\"type\": \"invalid_request_error\"")) {
            System.err.println(completion.getBody());
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        ImageResponseData data;
        try {
            data = mapper.readValue(completion.getBody(), ImageResponseData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String[] imageLinks = new String[data.getData().size()];

        for(int i = 0; i < data.getData().size(); i++) {
            ImageResponses imageResponse = data.getData().get(i);
            if(imageResponse == null) {
                continue;
            }
            String imageResponseUrl = imageResponse.getUrl();
            if(imageResponseUrl == null || imageResponseUrl.isEmpty()) {
                continue;
            }
            imageLinks[i] = imageResponseUrl;
        }

        return imageLinks;
    }

    public HttpClientInstance getHttpClientInstance() {
        return httpClientInstance;
    }

    @SuppressWarnings("unused")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @SuppressWarnings("unused")
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
