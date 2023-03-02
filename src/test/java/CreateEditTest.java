import io.github.jetkai.chatgpt.ChatGPT;
import io.github.jetkai.chatgpt.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.chatgpt.api.data.edit.EditData;
import io.github.jetkai.chatgpt.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.chatgpt.util.ReadApiKeyFromFile.getApiKeyFromFile;
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
