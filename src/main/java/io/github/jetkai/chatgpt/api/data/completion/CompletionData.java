package io.github.jetkai.chatgpt.api.data.completion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class CompletionData {

    /*
     * model
     * string
     * Required
     *
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     */
    private String model;

    /*
     * prompt
     * string or array
     * Optional
     *
     * Defaults to <|endoftext|>
     * The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens,
     * or array of token arrays.
     *
     * Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt
     * is not specified the model will generate as if from the beginning of a new document.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String prompt;

    /*
     * suffix
     * string
     * Optional
     * Defaults to null
     *
     * The suffix that comes after a completion of inserted text.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String suffix;

    /*
     * max_tokens
     * integer
     * Optional
     * Defaults to 16
     *
     * The maximum number of tokens to generate in the completion.
     *
     * The token count of your prompt plus max_tokens cannot exceed the model's context length.
     * Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("max_tokens")
    private int maxTokens;

    /*
     * temperature
     * number
     * Optional
     * Defaults to 1
     *
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the
     * output more random, while lower values like 0.2 will make it more focused and deterministic.
     *
     * We generally recommend altering this or top_p but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private double temperature;

    /*
     * top_p
     * number
     * Optional
     * Defaults to 1
     *
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers
     * the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the
     * top 10% probability mass are considered.
     *
     * We generally recommend altering this or temperature but not both.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("top_p")
    private double topP;

    /*
     * n
     * integer
     * Optional
     * Defaults to 1
     *
     * How many completions to generate for each prompt.
     *
     * Note: Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int n;

    /*
     * stream
     * boolean
     * Optional
     * Defaults to false
     *
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent
     * events asthey become available, with the stream terminated by a data: [DONE] message.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean stream;

    /*
     * logprobs
     * integer
     * Optional
     * Defaults to null
     *
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 5, the API will return a list of the 5 most likely tokens.
     * The API will always return the logprob of the sampled token, so there may be up to logprobs+1
     * elements in the response.
     *
     * The maximum value for logprobs is 5. If you need more than this, please contact us through our
     * Help center and describe your use case.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String logprobs;

    /*
     * echo
     * boolean
     * Optional
     * Defaults to false
     *
     * Echo back the prompt in addition to the completion
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean echo;

    /*
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     */
    private String stop;

    /*
     * presence_penalty
     * number
     * Optional
     * Defaults to 0
     *
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they
     * appear in the text so far, increasing the model's likelihood to talk about new topics.
     *
     * See more information about frequency and presence penalties.
     * (https://platform.openai.com/docs/api-reference/parameter-details)
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("presence_penalty")
    private double presencePenalty;

    /*
     * frequency_penalty
     * number
     * Optional
     * Defaults to 0
     *
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency
     * in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
     *
     * See more information about frequency and presence penalties.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("frequency_penalty")
    private double frequencyPenalty;

    /*
     * best_of
     * integer
     * Optional
     * Defaults to 1
     *
     * Generates best_of completions server-side and returns the "best" (the one with the highest log
     * probability per token). Results cannot be streamed.
     *
     * When used with n, best_of controls the number of candidate completions and n specifies how many to
     * return – best_of must be greater than n.
     *
     * Note: Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("best_of")
    private int bestOf;

    /*
     * logit_bias
     * map
     * Optional
     * Defaults to null
     *
     * Modify the likelihood of specified tokens appearing in the completion.
     *
     * Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to
     * an associated bias value from -100 to 100.
     * You can use this tokenizer tool (which works for both GPT-2 and GPT-3) to convert text to token IDs.
     * Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase
     * likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection
     * of the relevant token.
     *
     * As an example, you can pass {"50256": -100} to prevent the <|endoftext|> token from being generated.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Map<Object, Object> logitBias;

    /*
     * user
     * string
     * Optional
     * A unique identifier representing your end-user, which can help OpenAI to monitor
     * and detect abuse. Learn more.
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String user;

    public CompletionData() { }

    public CompletionData(String model, String prompt, int maxTokens, double temperature, double topP,
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

    public void setEcho(boolean echo) {
        this.echo = echo;
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

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setTopP(double topP) {
        this.topP = topP;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setFrequencyPenalty(double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public void setPresencePenalty(double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public void setLogitBias(Map<Object, Object> logitBias) {
        this.logitBias = logitBias;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public int getN() {
        return n;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTopP() {
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

    public String getSuffix() {
        return suffix;
    }

    public double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public double getPresencePenalty() {
        return presencePenalty;
    }

    public int getBestOf() {
        return bestOf;
    }

    public Map<Object, Object> getLogitBias() {
        return logitBias;
    }

    public String getUser() {
        return user;
    }

    public boolean isEcho() {
        return echo;
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
