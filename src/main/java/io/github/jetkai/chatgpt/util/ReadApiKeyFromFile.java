package io.github.jetkai.chatgpt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadApiKeyFromFile {

    public static ApiKeyFileData apiKeyFileData;

    public static ApiKeyFileData getApiKeyFromFile() {
        if(ReadApiKeyFromFile.apiKeyFileData != null) {
            return apiKeyFileData;
        }

        URL url = ReadApiKeyFromFile.class.getResource("ChatGPT-API-Key.json");

        Path path = null;

        try {
            if (url != null) {
                path = Path.of(url.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if(path == null) {
            return null;
        }

        String json;
        try {
            json = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        ApiKeyFileData data;
        try {
           data = mapper.readValue(json, ApiKeyFileData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(data != null) {
            apiKeyFileData = data;
            System.out.println("Loaded the API Key successfully.");
        }

        return apiKeyFileData;

    }

}
