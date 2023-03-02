package org.gpt.api.data.completion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class CompletionData {

    /*
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     */
    private String model;

    /*
     * The prompt(s) to generate completions for, encoded as a string, array of strings,
     * array of tokens, or array of token arrays.
     *
     * Note that <|endoftext|> is the document separator that the model sees during training,
     * so if a prompt is not specified the model will generate as if from the beginning of a new document.
     */
    private String prompt;

    /*
     * The maximum number of tokens to generate in the completion.
     *
     * The token count of your prompt plus max_tokens cannot exceed the model's context length.
     * Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
     */
    @JsonProperty("max_tokens")
    private int maxTokens;

    /*
     * What sampling temperature to use, between 0 and 2.
     * Higher values like 0.8 will make the output more random, while lower values like 0.2
     * will make it more focused and deterministic.
     *
     * We generally recommend altering this or top_p but not both.
     */
    private int temperature;

    /*
     * An alternative to sampling with temperature, called nucleus sampling,
     * where the model considers the results of the tokens with top_p probability mass.
     * So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     *
     * We generally recommend altering this or temperature but not both.
     */
    @JsonProperty("top_p")
    private int topP;

    /*
     * How many completions to generate for each prompt.
     *
     * Note: Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     */
    private int n;

    /*
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent events as
     * they become available, with the stream terminated by a data: [DONE] message.
     */
    private boolean stream;

    /*
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 5, the API will return a list of the 5 most likely tokens.
     * The API will always return the logprob of the sampled token, so there may be up to logprobs+1
     * elements in the response.
     *
     * The maximum value for logprobs is 5. If you need more than this, please contact us through our
     * Help center and describe your use case.
     */
    private String logprobs;

    /*
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     */
    private String stop;

    public CompletionData() { }

    public CompletionData(String model, String prompt, int maxTokens, int temperature, int topP,
                          int n, boolean stream, String logprobs, String stop) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
        this.n = n;
        this.stream = stream;
        this.logprobs = logprobs;
        this.stop = stop;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setTopP(int topP) {
        this.topP = topP;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public int getN() {
        return n;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getTopP() {
        return topP;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public String getModel() {
        return model;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getStop() {
        return stop;
    }

    public boolean isStream() {
        return stream;
    }

    @JsonIgnore
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
           json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}
