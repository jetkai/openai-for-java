package io.github.jetkai.openai.api.impl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.api.data.model.ModelPermissionsData;

import java.util.Optional;

/**
 * ModelPermissionsData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ModelPermissionsImpl extends ModelPermissionsData {

    @JsonProperty("id")
    private String id;
    @JsonProperty("object")
    private String object;
    @JsonProperty("created")
    private int created;
    @JsonProperty("allow_create_engine")
    private boolean allowCreateEngine;
    @JsonProperty("allow_sampling")
    private boolean allowSampling;
    @JsonProperty("allow_logprobs")
    private boolean allowLogprobs;
    @JsonProperty("allow_search_indices")
    private boolean allowSearchIndices;
    @JsonProperty("allow_view")
    private boolean allowView;
    @JsonProperty("allow_fine_tuning")
    private boolean allowFineTuning;
    @JsonProperty("organization")
    private String organization;
    @JsonProperty("group")
    private String group;
    @JsonProperty("isBlocking")
    private boolean isBlocking;

    public ModelPermissionsImpl() { }

    static ModelPermissionsImpl create(ModelPermissionsBuilderImpl builder) {
        return new ModelPermissionsImpl(builder);
    }

    private ModelPermissionsImpl(ModelPermissionsBuilderImpl builder) {
        this.allowCreateEngine = builder.allowCreateEngine;
        this.allowFineTuning = builder.allowFineTuning;
        this.id = builder.id;
        this.organization = builder.organization;
        this.created = builder.created;
        this.allowLogprobs = builder.allowLogprobs;
        this.allowSampling = builder.allowSampling;
        this.allowSearchIndices = builder.allowSearchIndices;
        this.allowView = builder.allowView;
        this.group = builder.group;
        this.isBlocking = builder.isBlocking;
        this.object = builder.object;
    }

    @Override
    public Optional<String> object() {
        return Optional.ofNullable(this.object);
    }

    @Override
    public Optional<String> id() {
        return Optional.ofNullable(this.id);
    }

    @Override
    public Optional<Integer> created() {
        return Optional.of(this.created);
    }

    @Override
    public Optional<Boolean> allowCreationEngine() {
        return Optional.of(this.allowCreateEngine);
    }

    @Override
    public Optional<Boolean> allowFineTuning() {
        return Optional.of(this.allowFineTuning);
    }

    @Override
    public Optional<Boolean> allowLogprobs() {
        return Optional.of(this.allowLogprobs);
    }

    @Override
    public Optional<Boolean> allowSampling() {
        return Optional.of(this.allowSampling);
    }

    @Override
    public Optional<Boolean> allowSearchIndices() {
        return Optional.of(this.allowSearchIndices);
    }

    @Override
    public Optional<Boolean> allowView() {
        return Optional.of(this.allowView);
    }

    @Override
    public Optional<Boolean> blocking() {
        return Optional.of(this.isBlocking);
    }

    @Override
    public Optional<String> group() {
        return Optional.ofNullable(this.group);
    }

    @Override
    public Optional<String> organization() {
        return Optional.ofNullable(this.organization);
    }

}