package io.github.jetkai.openai.impl.openai;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.*;
import io.github.jetkai.openai.net.HttpClientInstance;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

/**
 * OpenAI
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.1
 * {@code - 05/03/2023}
 */
final class OpenAIImpl extends OpenAI {

    private final HttpClient httpClient;
    private final HttpClientInstance httpClientInstance;
    private final String apiKey;
    private final String organization;
    private final GetModel model;
    private final GetModels models;
    private final CreateImageVariation imageVariation;
    private final CreateImageEdit imageEdit;
    private final CreateImage image;
    private final CreateEmbedding embedding;
    private final CreateEdit edit;
    private final CreateCompletion completion;
    private final CreateChatCompletion chatCompletion;
    private final CreateTranscription transcription;
    private final CreateTranscriptionTranslation transcriptionTranslation;
    private final CreateTranslation translation;

    static OpenAIImpl create(OpenAIBuilderImpl builder) {
        return new OpenAIImpl(builder);
    }

    private OpenAIImpl(OpenAIBuilderImpl builder) {
        this.model = builder.model;
        this.models = builder.models;
        this.edit = builder.edit;
        this.embedding = builder.embedding;
        this.image = builder.image;
        this.imageEdit = builder.imageEdit;
        this.imageVariation = builder.imageVariation;
        this.chatCompletion = builder.chatCompletion;
        this.completion = builder.completion;
        this.transcription = builder.transcription;
        this.transcriptionTranslation = builder.transcriptionTranslation;
        this.translation = builder.translation;
        this.apiKey = builder.apiKey;
        this.httpClient = builder.httpClient;
        this.organization = Objects.requireNonNullElse(builder.organization, "");
        this.httpClientInstance = Objects.requireNonNullElseGet(builder.httpClientInstance, HttpClientInstance::new);
    }

    @Override
    public <T> T sendRequest() {
        this.httpClientInstance.setHttpClient(this.httpClient != null
                ? this.httpClient
                : HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(30)) //30 seconds timeout
                .build()
        );
        if(model != null) {
            this.model.setOpenAI(this).initialize();
        }
        if(this.models != null) {
            this.models.setOpenAI(this).initialize();
        }
        if(this.translation != null) {
            this.translation.setOpenAI(this).initialize();
        }
        if(this.transcriptionTranslation != null) {
            this.transcriptionTranslation.setOpenAI(this).initialize();
        }
        if(this.transcription != null) {
            this.transcription.setOpenAI(this).initialize();
        }
        if(this.imageVariation != null) {
            this.imageVariation.setOpenAI(this).initialize();
        }
        if(this.imageEdit != null) {
            this.imageEdit.setOpenAI(this).initialize();
        }
        if(this.image != null) {
            this.image.setOpenAI(this).initialize();
        }
        if(this.embedding != null) {
            this.embedding.setOpenAI(this).initialize();
        }
        if(this.edit != null) {
            this.edit.setOpenAI(this).initialize();
        }
        if(this.completion != null) {
            this.completion.setOpenAI(this).initialize();
        }
        if(this.chatCompletion != null) {
            this.chatCompletion.setOpenAI(this).initialize();
        }
        return (T) this;
    }

    @Override
    public GetModels getModels() {
        return this.models;
    }

    @Override
    public GetModel getModel() {
        return this.model;
    }

    @Override
    public CreateImageVariation createImageVariation() {
        return this.imageVariation;
    }

    @Override
    public CreateTranscription createTranscription() {
        return this.transcription;
    }

    @Override
    public CreateTranscriptionTranslation createTranscriptionTranslation() {
        return this.transcriptionTranslation;
    }

    @Override
    public CreateTranslation createTranslation() {
        return this.translation;
    }

    @Override
    public CreateCompletion createCompletion() {
        return this.completion;
    }

    @Override
    public CreateChatCompletion createChatCompletion() {
        return this.chatCompletion;
    }

    @Override
    public CreateEdit createEdit() {
        return this.edit;
    }

    @Override
    public CreateImage createImage() {
        return this.image;
    }

    @Override
    public CreateImageEdit createImageEdit() {
        return this.imageEdit;
    }

    @Override
    public CreateEmbedding createEmbedding() {
        return this.embedding;
    }

    @Override
    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    @Override
    public HttpClientInstance getHttpClientInstance() {
        return this.httpClientInstance;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    @Override
    public String getOrganization() {
        return this.organization;
    }

    @Override
    public Optional<GetModel> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<GetModels> models() {
        return Optional.ofNullable(this.models);
    }

    @Override
    public Optional<CreateImageVariation> imageVariation() {
        return Optional.ofNullable(this.imageVariation);
    }

    @Override
    public Optional<CreateTranscription> transcription() {
        return Optional.ofNullable(this.transcription);
    }

    @Override
    public Optional<CreateTranslation> translation() {
        return Optional.ofNullable(this.translation);
    }

    @Override
    public Optional<CreateTranscriptionTranslation> transcriptionTranslation() {
        return Optional.ofNullable(this.transcriptionTranslation);
    }

    @Override
    public Optional<CreateCompletion> completion() {
        return Optional.ofNullable(this.completion);
    }

    @Override
    public Optional<CreateChatCompletion> chatCompletion() {
        return Optional.ofNullable(this.chatCompletion);
    }

    @Override
    public Optional<CreateEdit> edit() {
        return Optional.ofNullable(this.edit);
    }

    @Override
    public Optional<CreateImage> image() {
        return Optional.ofNullable(this.image);
    }

    @Override
    public Optional<CreateImageEdit> imageEdit() {
        return Optional.ofNullable(this.imageEdit);
    }

    @Override
    public Optional<CreateEmbedding> embedding() {
        return Optional.ofNullable(this.embedding);
    }

    @Override
    public Optional<HttpClient> httpClient() {
        return Optional.ofNullable(this.httpClient);
    }

    @Override
    public Optional<HttpClientInstance> httpClientInstance() {
        return Optional.ofNullable(this.httpClientInstance);
    }

    @Override
    public Optional<String> apiKey() {
        return Optional.ofNullable(this.apiKey);
    }

    @Override
    public Optional<String> organization() {
        return Optional.ofNullable(this.organization);
    }

}
