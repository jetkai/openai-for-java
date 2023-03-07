package io.github.jetkai.openai.api.impl.completion;

import io.github.jetkai.openai.api.data.completion.CompletionData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * CompletionBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 05/03/2023}
 */
@SuppressWarnings("unused")
public class CompletionBuilderImpl implements CompletionData.Builder {

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     */
    String model;

    /**
     * prompt
     * string or array
     * Optional
     * <p>
     * Defaults to <|endoftext|>
     * The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens,
     * or array of token arrays.
     * <p>
     * Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt
     * is not specified the model will generate as if from the beginning of a new document.
     */
    List<String> prompt;

    /**
     * suffix
     * string
     * Optional
     * Defaults to null
     * <p>
     * The suffix that comes after a completion of inserted text.
     */
    String suffix;

    /**
     * max_tokens
     * integer
     * Optional
     * Defaults to 16
     * <p>
     * The maximum number of tokens to generate in the completion.
     * <p>
     * The token count of your prompt plus max_tokens cannot exceed the model's context length.
     * Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
     */
    int maxTokens;

    /**
     * temperature
     * number
     * Optional
     * Defaults to 1
     * <p>
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the
     * output more random, while lower values like 0.2 will make it more focused and deterministic.
     * <p>
     * We generally recommend altering this or top_p but not both.
     */
    double temperature;

    /**
     * top_p
     * number
     * Optional
     * Defaults to 1
     * <p>
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers
     * the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the
     * top 10% probability mass are considered.
     * <p>
     * We generally recommend altering this or temperature but not both.
     */
    double topP;

    /**
     * n
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * How many completions to generate for each prompt.
     * <p>
     * Note: Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     */
    int n;

    /**
     * stream
     * boolean
     * Optional
     * Defaults to false
     * <p>
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent
     * events asthey become available, with the stream terminated by a data: [DONE] message.
     */
    boolean stream;

    /**
     * logprobs
     * integer
     * Optional
     * Defaults to null
     * <p>
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 5, the API will return a list of the 5 most likely tokens.
     * The API will always return the logprob of the sampled token, so there may be up to logprobs+1
     * elements in the response.
     * <p>
     * The maximum value for logprobs is 5. If you need more than this, please contact us through our
     * Help center and describe your use case.
     */
    String logprobs;

    /**
     * echo
     * boolean
     * Optional
     * Defaults to false
     * <p>
     * Echo back the prompt in addition to the completion
     */
    boolean echo;

    /**
     * stop
     * string or array
     * Optional
     * Defaults to null
     * <p>
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     */
    List<String> stop;

    /**
     * presence_penalty
     * number
     * Optional
     * Defaults to 0
     * <p>
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they
     * appear in the text so far, increasing the model's likelihood to talk about new topics.
     * <p>
     * See more information about frequency and presence penalties.
     * <a href="https://platform.openai.com/docs/api-reference/parameter-details">Learn more.</a>
     */
    double presencePenalty;

    /**
     * frequency_penalty
     * number
     * Optional
     * Defaults to 0
     * <p>
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency
     * in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
     * <p>
     * See more information about frequency and presence penalties.
     */
    double frequencyPenalty;

    /**
     * best_of
     * integer
     * Optional
     * Defaults to 1
     * <p>
     * Generates best_of completions server-side and returns the "best" (the one with the highest log
     * probability per token). Results cannot be streamed.
     * <p>
     * When used with n, best_of controls the number of candidate completions and n specifies how many to
     * return – best_of must be greater than n.
     * <p>
     * Note: Because this parameter generates many completions, it can quickly consume your token quota.
     * Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     */
    int bestOf;

    /**
     * logit_bias
     * map
     * Optional
     * Defaults to null
     * <p>
     * Modify the likelihood of specified tokens appearing in the completion.
     * <p>
     * Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to
     * an associated bias value from -100 to 100.
     * You can use this tokenizer tool (which works for both GPT-2 and GPT-3) to convert text to token IDs.
     * Mathematically, the bias is added to the logits generated by the model prior to sampling.
     * The exact effect will vary per model, but values between -1 and 1 should decrease or increase
     * likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection
     * of the relevant token.
     * <p>
     * As an example, you can pass {"50256": -100} to prevent the <|endoftext|> token from being generated.
     */
    Map<Object, Object> logitBias;

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor
     * and detect abuse. Learn more.
     */
    String user;


    @Override
    public CompletionData.Builder setEcho(boolean echo) {
        this.echo = echo;
        return this;
    }

    @Override
    public CompletionData.Builder setLogprobs(String logprobs) {
        requireNonNull(logprobs);
        this.logprobs = logprobs;
        return this;
    }

    @Override
    public CompletionData.Builder setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
        return this;
    }

    @Override
    public CompletionData.Builder setModel(String model) {
        requireNonNull(model);
        this.model = model;
        return this;
    }

    @Override
    public CompletionData.Builder setN(int n) {
        this.n = n;
        return this;
    }

    @Override
    public CompletionData.Builder setPrompt(List<String> prompt) {
        requireNonNull(prompt);
        this.prompt = prompt;
        return this;
    }

    @Override
    public CompletionData.Builder setPrompt(String prompt) {
        requireNonNull(prompt);
        this.prompt = Collections.singletonList(prompt);
        return this;
    }

    @Override
    public CompletionData.Builder setStop(String stop) {
        requireNonNull(stop);
        this.stop = Collections.singletonList(stop);
        return this;
    }

    @Override
    public CompletionData.Builder setStop(List<String> stop) {
        requireNonNull(stop);
        this.stop = stop;
        return this;
    }

    @Override
    public CompletionData.Builder setStream(boolean stream) {
        this.stream = stream;
        return this;
    }

    @Override
    public CompletionData.Builder setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public CompletionData.Builder setTopP(double topP) {
        this.topP = topP;
        return this;
    }

    @Override
    public CompletionData.Builder setSuffix(String suffix) {
        requireNonNull(suffix);
        this.suffix = suffix;
        return this;
    }

    @Override
    public CompletionData.Builder setFrequencyPenalty(double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
        return this;
    }

    @Override
    public CompletionData.Builder setPresencePenalty(double presencePenalty) {
        this.presencePenalty = presencePenalty;
        return this;
    }

    @Override
    public CompletionData.Builder setBestOf(int bestOf) {
        this.bestOf = bestOf;
        return this;
    }

    @Override
    public CompletionData.Builder setLogitBias(Map<Object, Object> logitBias) {
        requireNonNull(logitBias);
        this.logitBias = logitBias;
        return this;
    }

    @Override
    public CompletionData.Builder setUser(String user) {
        requireNonNull(user);
        this.user = user;
        return this;
    }

    @Override
    public CompletionData build() {
        return CompletionDataImpl.create(this);
    }
}
