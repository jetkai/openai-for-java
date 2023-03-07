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
import io.github.jetkai.openai.net.OpenAIEndpoints;

import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * OpenAI
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.1
 * {@code - 05/03/2023}
 */
final class OpenAIImpl extends OpenAI {

    private final HttpClient httpClient;
    private final HttpClientInstance httpClientInstance;
    private final String apiKey;
    private final String organization;
    private final ListModel model;
    private final ListModels models;
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
    private final String proxyIp;
    private final int proxyPort;
    private final Duration httpClientTimeout;

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
        this.organization = Objects.requireNonNullElse(builder.organization, "");
        this.httpClientTimeout = builder.httpClientTimeout;
        this.proxyIp = builder.proxyIp;
        this.proxyPort = builder.proxyPort;
        this.httpClient = builder.httpClientTimeout != null
                ? HttpClientInstance.customHttpClient(this.proxyIp, this.proxyPort, this.httpClientTimeout)
                : Objects.requireNonNullElse(builder.httpClient, HttpClientInstance.defaultHttpClient());
        this.httpClientInstance = Objects.requireNonNullElseGet(builder.httpClientInstance, () ->
                new HttpClientInstance(this.httpClient));
    }

    @Override
    public OpenAI sendRequest() {
        Stream.of(
                        model ,
                        models,
                        translation,
                        transcriptionTranslation,
                        transcription,
                        imageVariation,
                        imageEdit,
                        image,
                        embedding,
                        edit,
                        completion,
                        chatCompletion
                )
                .filter(Objects::nonNull)
                .forEach(request -> request.setOpenAI(this).initialize());
        return this;
    }

    public <T> T createInstance(Class<T> clazz, Object data) {
        if (clazz == null) {
            return (T) this;
        }
        Object instance;
        try {
            instance = clazz.getConstructor(data.getClass()).newInstance(data);
            if (instance instanceof OAPI) {
                ((OAPI) instance).setOpenAI(this).initialize();
            }
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return (T) instance;
    }

    public <T> T createInstance(OpenAIEndpoints endpoint, Object data) {
        OAPI instance;
        switch (endpoint) {
            case LIST_MODEL:
                if (!(data instanceof String)) {
                    throw new IllegalArgumentException("Data provided is not instance of String");
                }
                instance = new ListModel(String.valueOf(data));
                break;
            case LIST_MODELS:
                instance = new ListModels();
                break;
            case CREATE_CHAT_COMPLETION:
                if (!(data instanceof ChatCompletionData)) {
                    throw new IllegalArgumentException("Data provided is not instance of ChatCompletionData");
                }
                instance = new CreateChatCompletion((ChatCompletionData) data);
                break;
            case CREATE_COMPLETION:
                if (!(data instanceof CompletionData)) {
                    throw new IllegalArgumentException("Data provided is not instance of CompletionData");
                }
                instance = new CreateCompletion((CompletionData) data);
                break;
            case CREATE_EDIT:
                if (!(data instanceof EditData)) {
                    throw new IllegalArgumentException("Data provided is not instance of EditData");
                }
                instance = new CreateEdit((EditData) data);
                break;
            case CREATE_IMAGE:
                if (!(data instanceof ImageData)) {
                    throw new IllegalArgumentException("Data provided is not instance of ImageData");
                }
                instance = new CreateImage((ImageData) data);
                break;
            case CREATE_IMAGE_EDIT:
                if (!(data instanceof ImageEditData)) {
                    throw new IllegalArgumentException("Data provided is not instance of ImageEditData");
                }
                instance = new CreateImageEdit((ImageEditData) data);
                break;
            case CREATE_IMAGE_VARIATION:
                if (!(data instanceof ImageVariationData)) {
                    throw new IllegalArgumentException("Data provided is not instance of ImageVariationData");
                }
                instance = new CreateImageVariation((ImageVariationData) data);
                break;
            case CREATE_TRANSCRIPTION:
                if (!(data instanceof AudioData)) {
                    throw new IllegalArgumentException("Data provided is not instance of AudioData");
                }
                instance = new CreateTranscription((AudioData) data);
                break;
            case CREATE_TRANSCRIPTION_TRANSLATION:
                if (!(data instanceof AudioData)) {
                    throw new IllegalArgumentException("Data provided is not instance of AudioData");
                }
                instance = new CreateTranscriptionTranslation((AudioData) data);
                break;
            case CREATE_TRANSLATION:
                if (!(data instanceof CompletionData)) {
                    throw new IllegalArgumentException("Data provided is not instance of CompletionData");
                }
                instance = new CreateTranslation((CompletionData) data);
                break;
            case CREATE_EMBEDDING:
                if (!(data instanceof EmbeddingData)) {
                    throw new IllegalArgumentException("Data provided is not instance of EmbeddingData");
                }
                instance = new CreateEmbedding((EmbeddingData) data);
                break;
            default:
                throw new IllegalArgumentException("Endpoint not handled: " + endpoint);
        }
        instance.setOpenAI(this).initialize();
        return (T) instance;
    }

    @Override
    public ListModels getModels() {
        return this.models;
    }

    @Override
    public ListModel getModel() {
        return this.model;
    }

    @Override
    public CreateImageVariation getImageVariation() {
        return this.imageVariation;
    }

    @Override
    public CreateTranscription getTranscription() {
        return this.transcription;
    }

    @Override
    public CreateTranscriptionTranslation getTranscriptionTranslation() {
        return this.transcriptionTranslation;
    }

    @Override
    public CreateTranslation getTranslation() {
        return this.translation;
    }

    @Override
    public CreateCompletion getCompletion() {
        return this.completion;
    }

    @Override
    public CreateChatCompletion getChatCompletion() {
        return this.chatCompletion;
    }

    @Override
    public CreateEdit getEdit() {
        return this.edit;
    }

    @Override
    public CreateImage getImage() {
        return this.image;
    }

    @Override
    public CreateImageEdit getImageEdit() {
        return this.imageEdit;
    }

    @Override
    public CreateEmbedding getEmbedding() {
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
    public Optional<ListModel> model() {
        return Optional.ofNullable(this.model);
    }

    @Override
    public Optional<ListModels> models() {
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
