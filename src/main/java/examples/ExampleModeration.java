package examples;

import io.github.jetkai.openai.api.CreateModeration;
import io.github.jetkai.openai.api.data.moderation.ModerationData;
import io.github.jetkai.openai.api.data.moderation.response.results.ModerationResultsData;
import io.github.jetkai.openai.openai.OpenAI;

import java.util.Optional;

/**
 * ExampleModeration
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 13/03/2023}
 * @since 1.1.3
 * {@code - 13/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * <p>
 * You can get a free API key from <a href="https://platform.openai.com/account/api-keys">here</a>
 */
final class ExampleModeration {

    public static void main(String[] args) {
        //Initialize ExampleModeration class
        ExampleModeration moderation = new ExampleModeration();

        String model = "text-moderation-latest";
        String threat = "I will kill you and I hate you!";

        //Send request to API - response as JSON string
        ModerationResultsData resultsData = moderation.communicate(model, threat);

        //Print out true or false if the message is flagged by the moderation AI
        System.out.println("Threat: " + threat);
        System.out.println("isFlagged: " + resultsData.isFlagged());
    }

    private ModerationResultsData communicate(String model, String threat) {

        ModerationData moderationData = ModerationData.builder()
                .setModel(model)
                .setInput(threat)
                .build();

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createModeration(moderationData)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the CreateModeration API from OpenAI & create instance
        Optional<CreateModeration> optionalModeration = openAI.moderation();

        //Return model as a JSON string
        return optionalModeration.map(CreateModeration::asResults).orElse(null);
    }
    
}
