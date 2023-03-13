import io.github.jetkai.openai.api.CreateModeration;
import io.github.jetkai.openai.api.data.moderation.ModerationData;
import io.github.jetkai.openai.openai.OpenAI;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CreateModerationTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateModerationTest {

    @Test
    void createModerationTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Set model to view
        ModerationData moderationData = ModerationData.builder()
                .setModel("text-moderation-latest")
                .setInput("You are going to die and I hate you!")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                //Call the ListModel API from OpenAI & create instance
                .createModeration(moderationData)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();

        assertNotNull(openAI);

        Optional<CreateModeration> optionalModeration = openAI.moderation();
        assertFalse(optionalModeration.isEmpty());

        CreateModeration moderation = optionalModeration.get();

        //Test all the data parsed from OpenAI's json response
        assertAll("All data should be non-null",
                () -> assertNotNull(moderation),
                () -> assertNotNull(moderation.asData()),
                () -> assertNotNull(moderation.asResults()),
                () -> assertNotNull(moderation.asCategories()),
                () -> assertNotNull(moderation.asScores()),
                () -> assertTrue(moderation.isFlagged()),
                () -> assertNotNull(moderation.asJson()));
    }

}
