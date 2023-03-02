package io.github.jetkai.openai.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ApiKeyFileData {

    @JsonProperty("api_key")
    private String apiKey;
    private String organization;

    public ApiKeyFileData() { }

    public ApiKeyFileData(String apiKey, String organization) {
        this.apiKey = apiKey;
        this.organization = organization;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getOrganization() {
        return organization;
    }
}
