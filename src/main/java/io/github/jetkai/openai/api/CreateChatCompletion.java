package io.github.jetkai.openai.api;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.data.completion.chat.ChatCompletionData;
import io.github.jetkai.openai.api.data.completion.response.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.response.CompletionMessageData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.net.OpenAIEndpoints;
import io.github.jetkai.openai.net.RequestBuilder;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CreateChatCompletion
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateChatCompletion implements ApiInterface {

    /**
     * HttpResponse from OpenAI
     */
    private final AtomicReference<HttpResponse<String>> response;

    /**
     * The OpenAI instance
     */
    private final OpenAI openAI;

    /**
     * The endpoint that handleHttpResponse calls
     */
    private final OpenAIEndpoints endpoint;

    /**
     * The data that we are going to be sending through the POST request
     */
    private final ChatCompletionData completion;

    /**
     * Stored object of the data that has been deserialized from the OpenAI response
     */
    private CompletionResponseData data;

    /**
     * CreateChatCompletion
     * @param openAI - The OpenAI instance
     * @param completion - The completion data specified
     */
    public CreateChatCompletion(OpenAI openAI, ChatCompletionData completion) {
        this.openAI = openAI;
        this.completion = completion;
        this.endpoint = OpenAIEndpoints.CREATE_CHAT_COMPLETION;
        this.response = new AtomicReference<>();
        this.initialize();
    }

    /**
     * Initialize
     * Sends a HttpRequest & handles the response from OpenAI's API
     */
    private void initialize() {
        HttpResponse<String> httpResponse = response.get();
        if(httpResponse == null || httpResponse.body() == null || httpResponse.body().isEmpty()) {
            this.handleHttpResponse();
        }
    }

    /**
     * Reinitialize
     * If the HttpRequest/Response to OpenAI's API needs to be restarted, this will do that
     * @return CreateEdit
     */
    public CreateChatCompletion reinitialize() {
        this.data = null;
        this.handleHttpResponse();
        return this;
    }

    /**
     * HandleHttpResponse
     * This method will update the HttpResponse<String> response field with data from OpenAI
     * response.get().body() can then be called to retrieve the JSON response from OpenAI
     */
    private void handleHttpResponse() {
        openAI.getHttpClientInstance().getResponse(completion, new RequestBuilder() {
            @Override
            public HttpRequest request(Object data) {
                return buildPostRequest(endpoint.uri(), ((ChatCompletionData) data).toJson(),
                        openAI.getApiKey(), openAI.getOrganization()
                );
            }
        }).thenAccept(this.response::set).join();
    }

    public String asText() {

        if(this.data == null) {
            CompletionResponseData responseData = deserialize();
            if (responseData == null) {
                return null;
            }
            this.data = responseData;
        }

        StringBuilder builder = new StringBuilder();
        List<CompletionChoiceData> choiceList = data.getChoices();
        if(choiceList == null || choiceList.isEmpty()) {
            return null;
        }

        for (CompletionChoiceData choice : choiceList) {
            if (choice == null) {
                continue;
            }
            CompletionMessageData message = choice.getMessage();
            if (message == null) {
                continue;
            }
            String content = message.getContent();
            if (content == null || content.isEmpty()) {
                continue;
            }
            builder.append(content);
        }

        return builder.toString();
    }

    /**
     * asStringArray
     * @return - Text response from OpenAI GPT-3.5 as a StringArray
     */
    public String[] asStringArray() {

        if(this.data == null) {
            CompletionResponseData responseData = deserialize();
            if (responseData == null) {
                return null;
            }
            this.data = responseData;
        }

        List<CompletionChoiceData> choiceList = data.getChoices();
        String[] response = new String[choiceList.size()];

        for(int i = 0; i < choiceList.size(); i++) {
            CompletionChoiceData choice = choiceList.get(i);
            if (choice == null) {
                continue;
            }
            CompletionMessageData message = choice.getMessage();
            if (message == null) {
                continue;
            }
            String content = message.getContent();
            if (content == null || content.isEmpty()) {
                continue;
            }
            response[i] = content;
        }
        return response;

    }

    /**
     * asData
     * @return CompletionResponseData
     */
    @SuppressWarnings("unused")
    public CompletionResponseData asData() {
        if(this.data == null) {
            CompletionResponseData completion = deserialize();
            if (completion == null) {
                return null;
            }
            this.data = completion;
        }
        return this.data;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.data == null) {
            CompletionResponseData completion = deserialize();
            if (completion == null) {
                return null;
            }
            this.data = completion;
        }
        return this.data.toJson();
    }

    /**
     * deserializes
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return CompletionResponseData
     */
    private CompletionResponseData deserialize() {
        if(this.data == null) {
            CompletionResponseData completion = JacksonJsonDeserializer.parseData(
                    CompletionResponseData.class, this.getRawJsonResponse()
            );
            if (completion == null) {
                return null;
            }
            this.data = completion;
        }
        return this.data;
    }

    /**
     * getResponse
     * The response from OpenAI
     * @return AtomicReference<HttpResponse<String>>
     */
    @Override
    public AtomicReference<HttpResponse<String>> getHttpResponse() {
        return response;
    }

    /**
     * getBody
     * @return String (JSON from OpenAI response)
     */
    @Override
    public String getRawJsonResponse() {
        return String.valueOf(response.get().body());
    }

    /**
     * getEndpoint
     * @return OpenAIEndpoints (The endpoint that handleHttpResponse calls)
     */
    @Override
    public OpenAIEndpoints getEndpoint() {
        return endpoint;
    }

    @Override
    public ChatCompletionData getRequestData() {
        return completion;
    }

}
