package io.github.jetkai.openai.api.data.completion;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * CompletionData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.1
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */

@JsonSerialize
public abstract class CompletionData {

    public CompletionData() { }

    public static CompletionData.Builder builder() {
        return new CompletionBuilderImpl();
    }

    public static CompletionData create(String model, String prompt) {
        return builder().setModel(model)
                .setPrompt(prompt)
                .build();
    }

    public static CompletionData create(String model, List<String> prompt) {
        return builder().setModel(model)
                .setPrompt(prompt)
                .build();
    }

    public static CompletionData translation(String message, String toLanguage) {
        return builder()
                .setPrompt("Respond back to me in " + toLanguage + ":\n\n" + message)
                .setModel("text-davinci-003").build();
    }

    public static CompletionData translation(String message, String fromLanguage, String toLanguage) {
        return  builder()
                .setPrompt("Translate from " + fromLanguage + " to " + toLanguage + ":\n\n" + message)
                .setModel("text-davinci-003").build();
    }

    public interface Builder {
        Builder setEcho(boolean echo);
        Builder setLogprobs(String logprobs);
        Builder setMaxTokens(int maxTokens);
        Builder setModel(String model);
        Builder setN(int n);
        Builder setPrompt(List<String> prompt);
        Builder setPrompt(String prompt);
        Builder setStop(String stop);
        Builder setStop(List<String> stop);
        Builder setStream(boolean stream);
        Builder setTemperature(double temperature);
        Builder setTopP(double topP);
        Builder setSuffix(String suffix);
        Builder setFrequencyPenalty(double frequencyPenalty);
        Builder setPresencePenalty(double presencePenalty);
        Builder setBestOf(int bestOf);
        Builder setLogitBias(Map<Object, Object> logitBias);
        Builder setUser(String user);
        CompletionData build();
    }

    /**
     * echo
     * boolean
     * Optional
     * Defaults to false
     * <p>
     * Echo back the prompt in addition to the completion
     * @return echo
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("echo")
    public abstract boolean isEcho();

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
     * @return logprobs
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("logprobs")
    public abstract String getLogprobs();

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
     * @return maxTokens
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("max_tokens")
    public abstract int getMaxTokens();

    /**
     * model
     * string
     * Required
     * <p>
     * ID of the model to use. You can use the List models API to see all of your available models,
     * or see our Model overview for descriptions of them.
     * @return model
     */
    @JsonProperty("model")
    public abstract String getModel();

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
     * @return n
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("n")
    public abstract int getN();

    /**
     * prompt
     * string or array
     * Optional
     * <p>
     * Defaults to {@code <|endoftext|> }
     * The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens,
     * or array of token arrays.
     * <p>
     * Note that {@code <|endoftext|> } is the document separator that the model sees during training, so if a prompt
     * is not specified the model will generate as if from the beginning of a new document.
     * @return prompt
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("prompt")
    public abstract List<String> getPrompt();

    /**
     * stop
     * string or array
     * Optional
     * Defaults to null
     * <p>
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     * @return stop
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("stop")
    public abstract List<String> getStop();

    /**
     * stream
     * boolean
     * Optional
     * Defaults to false
     * <p>
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent
     * events asthey become available, with the stream terminated by a data: [DONE] message.
     * @return stream
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("stream")
    public abstract boolean isStream();

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
     * @return temperature
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("temperature")
    public abstract double getTemperature();


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
     * @return topP
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("top_p")
    public abstract double getTopP();

    /**
     * suffix
     * string
     * Optional
     * Defaults to null
     * <p>
     * The suffix that comes after a completion of inserted text.
     * @return suffix
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("suffix")
    public abstract String getSuffix();

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
     * @return frequencyPenalty
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("frequency_penalty")
    public abstract double getFrequencyPenalty();

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
     * @return presencePenalty
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("presence_penalty")
    public abstract double getPresencePenalty();

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
     * @return bestOf
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("best_of")
    public abstract int getBestOf();

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
     * As an example, you can pass {"50256": -100} to prevent the {@code <|endoftext|> } token from being generated.
     * @return logitBias
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("logit_bias")
    public abstract Map<Object, Object> getLogitBias();

    /**
     * user
     * string
     * Optional
     * <p>
     * A unique identifier representing your end-user, which can help OpenAI to monitor
     * and detect abuse. Learn more.
     * @return user
     */
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("user")
    public abstract String getUser();

    public abstract Optional<Boolean> echo();
    public abstract Optional<String> logprobs();
    public abstract Optional<Integer> maxTokens();
    public abstract Optional<String> model();
    public abstract Optional<Integer> n();
    public abstract Optional<List<String>> prompt();
    public abstract Optional<List<String>> stop();
    public abstract Optional<Boolean> stream();
    public abstract Optional<Double> temperature();
    public abstract Optional<Double> topP();
    public abstract Optional<String> suffix();
    public abstract Optional<Double> frequencyPenalty();
    public abstract Optional<Double> presencePenalty();
    public abstract Optional<Integer> bestOf();
    public abstract Optional<Map<Object, Object>> logitBias();
    public abstract Optional<String> user();

}
