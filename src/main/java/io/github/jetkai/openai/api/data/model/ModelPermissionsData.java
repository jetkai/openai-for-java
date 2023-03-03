package io.github.jetkai.openai.api.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * ModelPermissionsData
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
@SuppressWarnings("unused")
public class ModelPermissionsData {

    private String id;
    private String object;
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
    private String organization;
    private String group;
    private boolean isBlocking;

    public ModelPermissionsData() { }

    public ModelPermissionsData setObject(String object) {
        this.object = object;
        return this;
    }

    public ModelPermissionsData setId(String id) {
        this.id = id;
        return this;
    }

    public ModelPermissionsData setCreated(int created) {
        this.created = created;
        return this;
    }

    public ModelPermissionsData setAllowCreateEngine(boolean allowCreateEngine) {
        this.allowCreateEngine = allowCreateEngine;
        return this;
    }

    public ModelPermissionsData setAllowFineTuning(boolean allowFineTuning) {
        this.allowFineTuning = allowFineTuning;
        return this;
    }

    public ModelPermissionsData setAllowLogprobs(boolean allowLogprobs) {
        this.allowLogprobs = allowLogprobs;
        return this;
    }

    public ModelPermissionsData setAllowSampling(boolean allowSampling) {
        this.allowSampling = allowSampling;
        return this;
    }

    public ModelPermissionsData setAllowSearchIndices(boolean allowSearchIndices) {
        this.allowSearchIndices = allowSearchIndices;
        return this;
    }

    public ModelPermissionsData setAllowView(boolean allowView) {
        this.allowView = allowView;
        return this;
    }

    public ModelPermissionsData setBlocking(boolean blocking) {
        isBlocking = blocking;
        return this;
    }

    public ModelPermissionsData setGroup(String group) {
        this.group = group;
        return this;
    }

    public ModelPermissionsData setOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public String getObject() {
        return object;
    }

    public String getId() {
        return id;
    }

    public int getCreated() {
        return created;
    }

    public String getGroup() {
        return group;
    }

    public String getOrganization() {
        return organization;
    }

    public boolean isAllowCreateEngine() {
        return allowCreateEngine;
    }

    public boolean isAllowFineTuning() {
        return allowFineTuning;
    }

    public boolean isAllowLogprobs() {
        return allowLogprobs;
    }

    public boolean isAllowSampling() {
        return allowSampling;
    }

    public boolean isAllowSearchIndices() {
        return allowSearchIndices;
    }

    public boolean isAllowView() {
        return allowView;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    @JsonIgnore
    public String toJson() {
        return JacksonJsonDeserializer.valuesAsString(this);
    }

}