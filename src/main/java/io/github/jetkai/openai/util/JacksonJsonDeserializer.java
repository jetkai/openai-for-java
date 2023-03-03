package io.github.jetkai.openai.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JacksonJsonDeserializer
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class JacksonJsonDeserializer {

    /**
     * parseData
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @return Class T data structure
     */
    public static <T> T parseData(Class<T> clazz, String json) {
        if(json.contains("Incorrect API key")) {
            System.err.println("Incorrect API key provided: YOUR_API_KEY. " +
                    "You can find your API key at https://platform.openai");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        T data;
        try {
            data = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static <T> String valuesAsString(T clazz) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            json = mapper.writeValueAsString(clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

}
