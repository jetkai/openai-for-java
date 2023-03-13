package io.github.jetkai.openai.openai;

import io.github.jetkai.openai.api.*;
import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.api.data.embedding.EmbeddingData;
import io.github.jetkai.openai.api.data.image.ImageData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.api.data.moderation.ModerationData;
import io.github.jetkai.openai.net.HttpClientInstance;

import java.net.http.HttpClient;
import java.time.Duration;

import static java.util.Objects.requireNonNull;

/**
 * OpenAI
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.1
 * {@code - 05/03/2023}
 */
final class OpenAIBuilderImpl implements OpenAI.Builder {

    HttpClientInstance httpClientInstance;
    String apiKey;
    String organization;
    ListModel model;
    ListModels models;
    CreateImageVariation imageVariation;
    CreateModeration moderation;
    CreateImageEdit imageEdit;
    CreateImage image;
    CreateEmbedding embedding;
    CreateEdit edit;
    CreateCompletion completion;
    CreateChatCompletion chatCompletion;
    CreateTranscription transcription;
    CreateTranscriptionTranslation transcriptionTranslation;
    CreateTranslation translation;
    HttpClient httpClient;
    String proxyIp;
    int proxyPort;
    Duration httpClientTimeout;

    @Override
    public OpenAIBuilderImpl listModels() {
        this.models = new ListModels();
        return this;
    }

    @Override
    public OpenAIBuilderImpl listModel(String model) {
        requireNonNull(model, "\"model\" can not be null");
        this.model = new ListModel(model);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createImageVariation(ImageVariationData imageVariation) {
        requireNonNull(imageVariation, "\"imageVariation\" can not be null");
        this.imageVariation = new CreateImageVariation(imageVariation);
        return this;
    }

    @Override
    public OpenAI.Builder createModeration(ModerationData moderation) {
        requireNonNull(moderation, "\"moderationData\" can not be null");
        this.moderation = new CreateModeration(moderation);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createTranscription(AudioData transcription) {
        requireNonNull(transcription, "\"transcription\" can not be null");
        this.transcription = new CreateTranscription(transcription);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createTranscriptionTranslation(AudioData transcriptionTranslation) {
        requireNonNull(transcriptionTranslation, "\"transcriptionTranslation\" can not be null");
        this.transcriptionTranslation = new CreateTranscriptionTranslation(transcriptionTranslation);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createTranslation(CompletionData translation) {
        requireNonNull(translation, "\"translation\" can not be null");
        this.translation = new CreateTranslation(translation);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createCompletion(CompletionData completion) {
        requireNonNull(completion, "\"completion\" can not be null");
        this.completion = new CreateCompletion(completion);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createChatCompletion(ChatCompletionData chatCompletion) {
        requireNonNull(chatCompletion, "\"chatCompletion\" can not be null");
        this.chatCompletion = new CreateChatCompletion(chatCompletion);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createEdit(EditData edit) {
        requireNonNull(edit, "\"edit\" can not be null");
        this.edit = new CreateEdit(edit);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createImage(ImageData image) {
        requireNonNull(image, "\"image\" can not be null");
        this.image = new CreateImage(image);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createImageEdit(ImageEditData imageEdit) {
        requireNonNull(imageEdit, "\"imageEdit\" can not be null");
        this.imageEdit = new CreateImageEdit(imageEdit);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createEmbedding(EmbeddingData embedding) {
        requireNonNull(embedding, "\"embedding\" can not be null");
        this.embedding = new CreateEmbedding(embedding);
        return this;
    }

    @Override
    public OpenAIBuilderImpl setApiKey(String apiKey) {
        requireNonNull(apiKey, "\"apiKey\" can not be null");
        this.apiKey = apiKey;
        return this;
    }

    @Override
    public OpenAIBuilderImpl setOrganization(String organization) {
        requireNonNull(organization, "\"setOrganization\" can not be null");
        this.organization = organization;
        return this;
    }

    @Override
    public OpenAIBuilderImpl setHttpClient(HttpClient httpClient) {
        requireNonNull(httpClient, "\"httpClient\" can not be null");
        this.httpClient = httpClient;
        return this;
    }

    @Override
    public OpenAI.Builder setProxy(String ip, int port) {
        requireNonNull(ip, "\"ip\" can not be null");
        this.proxyIp = ip;
        this.proxyPort = port;
        return this;
    }

    @Override
    public OpenAI.Builder setTimeout(Duration duration) {
        requireNonNull(duration, "\"duration\" can not be null");
        this.httpClientTimeout = duration;
        return this;
    }

    @Override
    public OpenAI build() {
        return OpenAIImpl.create(this);
    }

}
