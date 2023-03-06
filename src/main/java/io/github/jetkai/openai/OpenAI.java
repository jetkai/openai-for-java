package io.github.jetkai.openai;

import io.github.jetkai.openai.api.*;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.impl.openai.OpenAIBuilderImpl;
import io.github.jetkai.openai.net.HttpClientInstance;

import java.net.http.HttpClient;
import java.util.Optional;

/**
 * OpenAI
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public abstract class OpenAI {

    public OpenAI() { }

    public static OpenAI newOpenAI() {
        return builder().build();
    }

    public static Builder builder() {
        return new OpenAIBuilderImpl();
    }

    public interface Builder {
        Builder getModel(String model);
        Builder getModels();
        Builder createEmbedding(EmbeddingData embedding);
        Builder createImageEdit(ImageEditData imageEdit);
        Builder createImage(ImageData image);
        Builder createEdit(EditData edit);
        Builder createChatCompletion(ChatCompletionData chatCompletion);
        Builder createCompletion(CompletionData completion);
        Builder createTranslation(CompletionData translation);
        Builder createTranscriptionTranslation(AudioData transcriptionTranslation);
        Builder createTranscription(AudioData transcription);
        Builder createImageVariation(ImageVariationData imageVariation);
        Builder setApiKey(String apiKey);
        Builder setOrganization(String organization);
        Builder setHttpClient(HttpClient httpClient);
        OpenAI build();
    }

    public abstract <T> T sendRequest();

    public abstract GetModels getModels();

    public abstract GetModel getModel();

    public abstract CreateImageVariation createImageVariation();

    public abstract CreateTranscription createTranscription();

    public abstract CreateTranscriptionTranslation createTranscriptionTranslation();

    public abstract CreateTranslation createTranslation();

    public abstract CreateCompletion createCompletion();

    public abstract CreateChatCompletion createChatCompletion();

    public abstract CreateEdit createEdit();

    public abstract CreateImage createImage();

    public abstract CreateImageEdit createImageEdit();

    public abstract CreateEmbedding createEmbedding();

    public abstract HttpClient getHttpClient();

    public abstract HttpClientInstance getHttpClientInstance();

    public abstract String getApiKey();

    public abstract String getOrganization();

    public abstract Optional<GetModels> models();

    public abstract Optional<GetModel> model();

    public abstract Optional<CreateImageVariation> imageVariation();

    public abstract Optional<CreateTranscription> transcription();

    public abstract Optional<CreateTranscriptionTranslation> transcriptionTranslation();

    public abstract Optional<CreateTranslation> translation();

    public abstract Optional<CreateCompletion> completion();

    public abstract Optional<CreateChatCompletion> chatCompletion();

    public abstract Optional<CreateEdit> edit();

    public abstract Optional<CreateImage> image();

    public abstract Optional<CreateImageEdit> imageEdit();

    public abstract Optional<CreateEmbedding> embedding();

    public abstract Optional<HttpClient> httpClient();

    public abstract Optional<HttpClientInstance> httpClientInstance();

    public abstract Optional<String> apiKey();

    public abstract Optional<String> organization();

}
