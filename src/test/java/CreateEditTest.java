import io.github.jetkai.openai.OpenAI;
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
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        EditData edit = new EditData();
        edit.setModel("text-davinci-edit-001");
        edit.setInput("What day of the wek is it?");
        edit.setInstruction("Fix the spelling mistakes");

        CompletionResponseData data = openAI.createEdit(edit).asData(); //You can call "data" to see the response

        assertFalse(data.getChoices().isEmpty());
    }

}
