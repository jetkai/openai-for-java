package io.github.jetkai.openai.api.data.model;

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

    public ModelPermissions(String id, String object, int created, boolean allowCreateEngine, boolean allowSampling,
                            boolean allowLogprobs, boolean allowSearchIndices, boolean allowView, boolean allowFineTuning,
                            String organization, String group, boolean isBlocking) {
        this.id = id;
        this.created = created;
        this.allowCreateEngine = allowCreateEngine;
        this.allowSampling = allowSampling;
        this.allowLogprobs = allowLogprobs;
        this.allowSearchIndices = allowSearchIndices;
        this.allowView = allowView;
        this.allowFineTuning = allowFineTuning;
        this.organization = organization;
        this.group = group;
        this.isBlocking = isBlocking;
    }

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