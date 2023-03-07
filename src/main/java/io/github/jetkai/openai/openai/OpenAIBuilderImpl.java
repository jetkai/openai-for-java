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
        requireNonNull(model);
        this.model = new ListModel(model);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createImageVariation(ImageVariationData imageVariation) {
        requireNonNull(imageVariation);
        this.imageVariation = new CreateImageVariation(imageVariation);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createTranscription(AudioData transcription) {
        requireNonNull(transcription);
        this.transcription = new CreateTranscription(transcription);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createTranscriptionTranslation(AudioData transcriptionTranslation) {
        requireNonNull(transcriptionTranslation);
        this.transcriptionTranslation = new CreateTranscriptionTranslation(transcriptionTranslation);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createTranslation(CompletionData translation) {
        requireNonNull(translation);
        this.translation = new CreateTranslation(translation);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createCompletion(CompletionData completion) {
        requireNonNull(completion);
        this.completion = new CreateCompletion(completion);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createChatCompletion(ChatCompletionData chatCompletion) {
        requireNonNull(chatCompletion);
        this.chatCompletion = new CreateChatCompletion(chatCompletion);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createEdit(EditData edit) {
        requireNonNull(edit);
        this.edit = new CreateEdit(edit);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createImage(ImageData image) {
        requireNonNull(image);
        this.image = new CreateImage(image);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createImageEdit(ImageEditData imageEdit) {
        requireNonNull(imageEdit);
        this.imageEdit = new CreateImageEdit(imageEdit);
        return this;
    }

    @Override
    public OpenAIBuilderImpl createEmbedding(EmbeddingData embedding) {
        requireNonNull(embedding);
        this.embedding = new CreateEmbedding(embedding);
        return this;
    }

    @Override
    public OpenAIBuilderImpl setApiKey(String apiKey) {
        requireNonNull(apiKey);
        this.apiKey = apiKey;
        return this;
    }

    @Override
    public OpenAIBuilderImpl setOrganization(String organization) {
        requireNonNull(organization);
        this.organization = organization;
        return this;
    }

    @Override
    public OpenAIBuilderImpl setHttpClient(HttpClient httpClient) {
        requireNonNull(httpClient);
        this.httpClient = httpClient;
        return this;
    }

    @Override
    public OpenAI.Builder setProxy(String ip, int port) {
        requireNonNull(ip);
        this.proxyIp = ip;
        this.proxyPort = port;
        return this;
    }

    @Override
    public OpenAI.Builder setTimeout(Duration duration) {
        requireNonNull(duration);
        this.httpClientTimeout = duration;
        return this;
    }

    @Override
    public OpenAI build() {
        return OpenAIImpl.create(this);
    }

}
