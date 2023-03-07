package examples;

import io.github.jetkai.openai.api.CreateEdit;
import io.github.jetkai.openai.api.data.edit.EditData;
import io.github.jetkai.openai.openai.OpenAI;

import java.util.Optional;

/**
 * ExampleSpellingCorrection
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * </p>
 */
final class ExampleSpellingCorrection {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     *     OpenAI openAI = OpenAI.builder()
     *             .setApiKey("YOUR_API_KEY")
     *             .setOrganization("YOUR_ORGANIZATION")
     *             .build();
     */

    public static void main(String[] args) {
        //Initialize ExampleSpellingCorrection class
        ExampleSpellingCorrection getModel = new ExampleSpellingCorrection();

        String spellingMistake = "Wha dai of the wek is it?";
        String instruction = "Fix the spelling mistakes";

        //Send request to API - response as string
        String spellingCorrection = getModel.communicate(spellingMistake, instruction);

        //Print out the mistake & correction
        System.out.println("Mistake: " + spellingMistake);
        System.out.println("Correction: " + spellingCorrection);
    }

    private String communicate(String spellingMistake, String instruction) {
        //Call the ListModels API from OpenAI & create instance
        //You can also specify the model and use the following:
        //EditData.create(model, spellingMistake, instruction);
        EditData editData = EditData.create(spellingMistake, instruction);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createEdit(editData)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the CreateEdit API from OpenAI & create instance
        Optional<CreateEdit> createEdit = openAI.edit();

        //Return model as a JSON string
        return createEdit.map(CreateEdit::asText).orElse(null);
    }

}
