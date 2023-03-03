import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateEdit;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
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
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //EditData, ready to send to the OpenAI Api
        EditData edit = new EditData();

        //ID of the model to use. You can use the text-davinci-edit-001 or
        //code-davinci-edit-001 model with this endpoint.
        edit.setModel("text-davinci-edit-001");

        //The input text to use as a starting point for the edit.
        edit.setInput("What day of the wek is it?");

        //The instruction that tells the model how to edit the prompt.
        edit.setInstruction("Fix the spelling mistakes");

        //Call the CreateEdit API from OpenAI & create instance
        CreateEdit createEdit = openAI.createEdit(edit); //You can call "data" to see the response

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
