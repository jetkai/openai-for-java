package io.github.jetkai.openai.api.data.completion.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.github.jetkai.openai.api.data.completion.choice.CompletionChoiceData;
import io.github.jetkai.openai.api.data.completion.usage.CompletionUsageData;
import io.github.jetkai.openai.api.impl.completion.response.CompletionResponseDataBuilderImpl;

import java.util.List;
import java.util.Optional;

/**
 * CompletionResponseData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonDeserialize(builder = CompletionResponseData.Builder.class)
public abstract class CompletionResponseData {

    public CompletionResponseData() { }

    public static CompletionResponseData.Builder builder() {
        return new CompletionResponseDataBuilderImpl();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("created")
        Builder setCreated(int created);

        @JsonProperty("model")
        Builder setModel(String model);

        @JsonProperty("object")
        Builder setObject(String object);

        @JsonProperty("id")
        Builder setId(String id);

        @JsonProperty("choices")
        Builder setChoices(List<CompletionChoiceData> choices);

        @JsonProperty("usage")
        Builder setUsage(CompletionUsageData usage);

        CompletionResponseData build();

        @JsonCreator
        static Builder create() {
            return new CompletionResponseDataBuilderImpl();
        }
    }

    @JsonProperty("created")
    public abstract int getCreated();

    @JsonProperty("model")
    public abstract String getModel();

    @JsonProperty("object")
    public abstract String getObject();

    @JsonProperty("id")
    public abstract String getId();

    @JsonProperty("choices")
    public abstract List<CompletionChoiceData> getChoices();

    @JsonProperty("usage")
    public abstract CompletionUsageData getUsage();

    public abstract Optional<Integer> created();

    public abstract Optional<String> model();

    public abstract Optional<String> object();

    public abstract Optional<String> id();

    public abstract Optional<List<CompletionChoiceData>> choices();

    public abstract Optional<CompletionUsageData> usage();
}