import org.gpt.ChatGPT;
import org.gpt.api.data.completion.response.CompletionResponseData;
import org.gpt.api.data.edit.EditData;
import org.gpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static org.gpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEditTest {

    @Test
    void createEditTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the ChatGPT Api
        EditData edit = new EditData();
        edit.setModel("text-davinci-edit-001");
        edit.setInput("What day of the wek is it?");
        edit.setInstruction("Fix the spelling mistakes");

        CompletionResponseData data = gpt.createEditResponse(edit); //You can call "data" to see the response

        assertFalse(data.getChoices().isEmpty());
    }

}
