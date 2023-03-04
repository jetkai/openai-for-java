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
        T data = null;
        try {
            if (json.contains("\"error\":")) {
                throw new Exception(json);
            }

            ObjectMapper mapper = new ObjectMapper();
            try {
                data = mapper.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
