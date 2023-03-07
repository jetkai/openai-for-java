import io.github.jetkai.openai.api.CreateEdit;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateEditTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateEditTest {

    @Test
    void createEditTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //EditData, ready to send to the OpenAI Api
        EditData edit = EditData.builder()
                //ID of the model to use. You can use the text-davinci-edit-001 or
                //code-davinci-edit-001 model with this endpoint.
                .setModel("text-davinci-edit-001")

                //The input text to use as a starting point for the edit.
                .setInput("What day of the wek is it?")

                //The instruction that tells the model how to edit the prompt.
                .setInstruction("Fix the spelling mistakes")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createEdit(edit)
                .build()
                .sendRequest();

        assertNotNull(openAI);

        //Call the CreateEdit API from OpenAI & create instance
        Optional<CreateEdit> optionalCreateEdit = openAI.edit(); //You can call "data" to see the response
        assertFalse(optionalCreateEdit.isEmpty());

        CreateEdit createEdit = optionalCreateEdit.get();

        //Data structure example
        CompletionResponseData responseData = createEdit.asData();
        assertNotNull(responseData);

        //StringArray example
        String[] stringArray = createEdit.asStringArray();
        assertNotNull(stringArray);

        //Json example
        String json = createEdit.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
