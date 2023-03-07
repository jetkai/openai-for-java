package io.github.jetkai.openai.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Objects;

/**
 * JacksonJsonDeserializer
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 03/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class JacksonJsonDeserializer {

    private static final ObjectMapper mapper = new ObjectMapper()
            .setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP))
            .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    /**
     * parseData
     * Parses the JSON response from OpenAI and deserializes the string to the below data structure
     * @param clazz - The class file to parse as
     * @param json - The json string to parse
     * @param <T> - The parsed data structure as class
     * @return - Deserialized data structure
     */
    public static <T> T parseData(Class<T> clazz, String json) {
        Objects.requireNonNull(clazz, "clazz is null");
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String valuesAsString(T clazz) {
        Objects.requireNonNull(clazz, "clazz is null");
        try {
            return mapper.writeValueAsString(clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
