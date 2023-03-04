package io.github.jetkai.openai;

import io.github.jetkai.openai.api.*;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.api.data.transcription.TranscriptionData;
import io.github.jetkai.openai.api.data.translation.TranslationData;
import io.github.jetkai.openai.net.HttpClientInstance;

/**
 * OpenAI
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class OpenAI {

    public final HttpClientInstance httpClientInstance = new HttpClientInstance();
    private boolean alwaysNewInstance = true;
    private String apiKey;
    private String organization;

    private GetModel model;
    private GetModels models;
    private CreateImageVariation imageVariation;
    private CreateImageEdit imageEdit;
    private CreateImage image;
    private CreateEmbedding embedding;
    private CreateEdit edit;
    private CreateCompletion completion;
    private CreateChatCompletion chatCompletion;
    private CreateTranscription transcription;
    private CreateTranslation translation;

    public OpenAI() {
        this.apiKey = "";
        this.organization = "";
    }

    public OpenAI(String apiKey) {
        this.apiKey = apiKey;
        this.organization = "";
    }

    public OpenAI(String apiKey, String organization) {
        this.apiKey = apiKey;
        this.organization = organization;
    }

    @SuppressWarnings("unused")
    public static OpenAI createInstance() {
        return new OpenAI();
    }

    @SuppressWarnings("unused")
    public static OpenAI createInstance(String apiKey) {
        return new OpenAI(apiKey);
    }

    @SuppressWarnings("unused")
    public static OpenAI createInstance(String apiKey, String organization) {
        return new OpenAI(apiKey, organization);
    }

    public GetModels getModels() {
        if(models == null || alwaysNewInstance) {
            models = new GetModels(this);
        }
        return models;
    }

    public GetModel getModel(String modelName) {
        if(model == null || alwaysNewInstance) {
            model = new GetModel(this, modelName);
        }
        return model;
    }

    public CreateImageVariation createImageVariation(ImageVariationData imageData) {
        if(imageVariation == null || alwaysNewInstance) {
            imageVariation = new CreateImageVariation(this, imageData);
        }
        return imageVariation;
    }

    public CreateTranscription createTranscription(TranscriptionData transcriptionData) {
        if(transcription == null || alwaysNewInstance) {
            transcription = new CreateTranscription(this, transcriptionData);
        }
        return transcription;
    }

    public CreateTranslation createTranslation(TranslationData translationData) {
        if(translation == null || alwaysNewInstance) {
            translation = new CreateTranslation(this, translationData);
        }
        return translation;
    }

    public CreateCompletion createCompletion(CompletionData completionData) {
        if(completion == null || alwaysNewInstance) {
            completion = new CreateCompletion(this, completionData);
        }
        return completion;
    }

    public CreateChatCompletion createChatCompletion(ChatCompletionData completionData) {
        if(chatCompletion == null || alwaysNewInstance) {
            chatCompletion = new CreateChatCompletion(this, completionData);
        }
        return chatCompletion;
    }

    @SuppressWarnings("unused")
    public CreateEdit createEdit(EditData editData) {
        if(edit == null || alwaysNewInstance) {
            edit = new CreateEdit(this, editData);
        }
        return edit;
    }

    public CreateImage createImage(ImageData imageData) {
        if(image == null || alwaysNewInstance) {
            image = new CreateImage(this, imageData);
        }
        return image;
    }

    public CreateImageEdit createImageEdit(ImageEditData imageData) {
        if(imageEdit == null || alwaysNewInstance) {
            imageEdit = new CreateImageEdit(this, imageData);
        }
        return imageEdit;
    }

    public CreateEmbedding createEmbedding(EmbeddingData embeddingData) {
        if(embedding == null || alwaysNewInstance) {
            embedding = new CreateEmbedding(this, embeddingData);
        }
        return embedding;
    }

    public HttpClientInstance getHttpClientInstance() {
        return httpClientInstance;
    }

    public void setAlwaysNewInstance(boolean alwaysNewInstance) {
        this.alwaysNewInstance = alwaysNewInstance;
    }

    @SuppressWarnings("unused")
    public boolean isAlwaysNewInstance() {
        return alwaysNewInstance;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public OpenAI setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @SuppressWarnings({"unused", "UnusedReturnValue"})
    public OpenAI setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getOrganization() {
        return organization;
    }

}
