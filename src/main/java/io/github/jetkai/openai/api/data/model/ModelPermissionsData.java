package io.github.jetkai.openai.api.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.impl.model.permissions.ModelPermissionsBuilderImpl;

import java.util.Optional;

/**
 * ModelPermissionsData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonSerialize
@JsonDeserialize(builder = ModelPermissionsData.Builder.class)
@SuppressWarnings("unused")
public abstract class ModelPermissionsData {

    public ModelPermissionsData() { }

    public static ModelPermissionsData create() {
        return builder().build();
    }

    public static ModelPermissionsData.Builder builder() {
        return new ModelPermissionsBuilderImpl();
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public interface Builder {
        @JsonProperty("object")
        Builder setObject(String object);
        @JsonProperty("id")
        Builder setId(String id);
        @JsonProperty("created")
        Builder setCreated(int created);
        @JsonProperty("allow_create_engine")
        Builder setAllowCreateEngine(boolean allowCreateEngine);
        @JsonProperty("allow_fine_tuning")
        Builder setAllowFineTuning(boolean allowFineTuning);
        @JsonProperty("allow_logprobs")
        Builder setAllowLogprobs(boolean allowLogprobs);
        @JsonProperty("allow_sampling")
        Builder setAllowSampling(boolean allowSampling);
        @JsonProperty("allow_search_indices")
        Builder setAllowSearchIndices(boolean allowSearchIndices);
        @JsonProperty("allow_view")
        Builder setAllowView(boolean allowView);
        @JsonProperty("is_blocking")
        Builder setBlocking(boolean blocking);
        @JsonProperty("group")
        Builder setGroup(String group);
        @JsonProperty("organization")
        Builder setOrganization(String organization);
        ModelPermissionsData build();

        @JsonCreator
        static Builder create() {
            return new ModelPermissionsBuilderImpl();
        }
    }

    @JsonProperty("object")
    public abstract String getObject();

    @JsonProperty("id")
    public abstract String getId();

    @JsonProperty("created")
    public abstract int getCreated();

    @JsonProperty("allow_create_engine")
    public abstract boolean isAllowCreationEngine();

    @JsonProperty("allow_fine_tuning")
    public abstract boolean isAllowFineTuning();

    @JsonProperty("allow_logprobs")
    public abstract boolean isAllowLogprobs();

    @JsonProperty("allow_sampling")
    public abstract boolean isAllowSampling();

    @JsonProperty("allow_search_indices")
    public abstract boolean isAllowSearchIndices();

    @JsonProperty("allow_view")
    public abstract boolean isAllowView();

    @JsonProperty("is_blocking")
    public abstract boolean isBlocking();

    @JsonProperty("group")
    public abstract String getGroup();

    @JsonProperty("organization")
    public abstract String getOrganization();

    public abstract Optional<String> object();
    public abstract Optional<String> id();
    public abstract Optional<Integer> created();
    public abstract Optional<Boolean> allowCreationEngine();
    public abstract Optional<Boolean> allowFineTuning();
    public abstract Optional<Boolean> allowLogprobs();
    public abstract Optional<Boolean> allowSampling();
    public abstract Optional<Boolean> allowSearchIndices();
    public abstract Optional<Boolean> allowView();
    public abstract Optional<Boolean> blocking();
    public abstract Optional<String> group();
    public abstract Optional<String> organization();

}