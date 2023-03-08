import io.github.jetkai.openai.api.data.model.ModelData;
import io.github.jetkai.openai.util.JacksonJsonDeserializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JacksonJsonTests {

    @Test
    public void jacksonDeserializeTest() {
        // Test parsing a valid JSON string into a given class

        String modelJson = "{\n" +
                "  \"id\": \"davinci\",\n" +
                "  \"object\": \"model\",\n" +
                "  \"created\": 1649359874,\n" +
                "  \"owned_by\": \"openai\",\n" +
                "  \"permission\": [\n" +
                "    {\n" +
                "      \"id\": \"modelperm-U6ZwlyAd0LyMk4rcMdz33Yc3\",\n" +
                "      \"object\": \"model_permission\",\n" +
                "      \"created\": 1669066355,\n" +
                "      \"allow_create_engine\": false,\n" +
                "      \"allow_sampling\": true,\n" +
                "      \"allow_logprobs\": true,\n" +
                "      \"allow_search_indices\": false,\n" +
                "      \"allow_view\": true,\n" +
                "      \"allow_fine_tuning\": false,\n" +
                "      \"organization\": \"*\",\n" +
                "      \"group\": null,\n" +
                "      \"is_blocking\": false\n" +
                "    }\n" +
                "  ],\n" +
                "  \"root\": \"davinci\",\n" +
                "  \"parent\": null\n" +
                "}";

        ModelData modelData = JacksonJsonDeserializer.parseData(ModelData.class, modelJson);
        assertNotNull(modelData);
        assertEquals("davinci", modelData.getId());
        assertEquals("model", modelData.getObject());
        assertEquals("openai", modelData.getOwnedBy());
        assertEquals("davinci", modelData.getRoot());

        // Test handling an invalid JSON string
        String badModelJson = "" +
                "  \"id\": \"davinci\",\n" +
                "  \"object\": -1,\n" +
                "  \"created\": 1649359874,\n" +
                "  \"owned_by\": \"openai\",\n" +
                "  \"permission\": [\n" +
                "    {\n" +
                "      \"id\": \"modelperm-U6ZwlyAd0LyMk4rcMdz33Yc3\",\n" +
                "      \"object\": \"model_permission\",\n" +
                "      \"created\": 1669066355,\n" +
                "      \"allow_create_engine\": false,\n" +
                "      \"allow_sampling\": true,\n" +
                "      \"allow_logprobs\": true,\n" +
                "      \"allow_search_indices\": false,\n" +
                "      \"allow_view\": true,\n" +
                "      \"allow_fine_tuning\": false,\n" +
                "      \"organization\": \"*\",\n" +
                "      \"group\": null,\n" +
                "      \"is_blocking\": false\n" +
                "    }\n" +
                "  \"root\": \"davinci\",\n" +
                "  \"parent\": null\n" +
                "}Sz__@@\"";
        assertThrows(RuntimeException.class, () -> JacksonJsonDeserializer.parseData(ModelData.class, badModelJson),
                "Invalid JSON string should throw a JsonProcessingException");
    }

    @Test
    public void jacksonSerializeTest() {
        // Test serializing a given object to JSON
        ModelData person = ModelData.builder()
                .setObject("model")
                .setId("davinci")
                .setOwnedBy("openai")
                .build();
        String jsonString = JacksonJsonDeserializer.valuesAsString(person);
        assertNotNull(jsonString);

        // Test handling a null input
        assertThrows(NullPointerException.class, () -> JacksonJsonDeserializer.valuesAsString(null),
                "Null input should throw a NullPointerException");
    }

}
