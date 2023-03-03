package io.github.jetkai.openai.api.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;

/**
 * ModelPermissions
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
public class ModelPermissions {

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

    public ModelPermissions() { }

    public void setObject(String object) {
        this.object = object;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public void setAllowCreateEngine(boolean allowCreateEngine) {
        this.allowCreateEngine = allowCreateEngine;
    }

    public void setAllowFineTuning(boolean allowFineTuning) {
        this.allowFineTuning = allowFineTuning;
    }

    public void setAllowLogprobs(boolean allowLogprobs) {
        this.allowLogprobs = allowLogprobs;
    }

    public void setAllowSampling(boolean allowSampling) {
        this.allowSampling = allowSampling;
    }

    public void setAllowSearchIndices(boolean allowSearchIndices) {
        this.allowSearchIndices = allowSearchIndices;
    }

    public void setAllowView(boolean allowView) {
        this.allowView = allowView;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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