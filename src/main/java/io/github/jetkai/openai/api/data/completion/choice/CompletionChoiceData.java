package io.github.jetkai.openai.api.data.completion.choice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.completion.message.CompletionMessageData;

import java.util.Optional;

/**
 * CompletionChoiceData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = CompletionChoiceData.Builder.class)
public abstract class CompletionChoiceData {

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("id")
        Builder setId(String id);

        @JsonProperty("created")
        Builder setCreated(int created);

        @JsonProperty("object")
        Builder setObject(String object);

        @JsonProperty("message")
        Builder setMessage(CompletionMessageData message);

        @JsonProperty("logprobs")
        Builder setLogprobs(String logprobs);

        @JsonProperty("finish_reason")
        Builder setFinishReason(String finishReason);

        @JsonProperty("index")
        Builder setIndex(int index);

        @JsonProperty("text")
        Builder setText(String text);

        CompletionChoiceData build();

        @JsonCreator
        static Builder create() {
            return new CompletionChoiceBuilderImpl();
        }
    }

    public static CompletionChoiceData.Builder builder() {
        return new CompletionChoiceBuilderImpl();
    }

    @JsonProperty("id")
    public abstract String getId();

    @JsonProperty("created")
    public abstract int getCreated();

    @JsonProperty("object")
    public abstract String getObject();

    @JsonProperty("message")
    public abstract CompletionMessageData getMessage();

    @JsonProperty("logprobs")
    public abstract String getLogprobs();

    @JsonProperty("finish_reason")
    public abstract String getFinishReason();

    @JsonProperty("index")
    public abstract int getIndex();

    @JsonProperty("text")
    public abstract String getText();

    public abstract Optional<String> id();

    public abstract Optional<Integer> created();

    public abstract Optional<String> object();

    public abstract Optional<CompletionMessageData> message();

    public abstract Optional<String> logprobs();

    public abstract Optional<String> finishReason();

    public abstract Optional<Integer> index();

    public abstract Optional<String> text();

}
