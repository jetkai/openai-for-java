package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateEdit;
import io.github.jetkai.openai.api.data.edit.EditData;

/**
 * ExampleSpellingCorrection
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * </p>
 */
public class ExampleSpellingCorrection {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

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
        //Call the GetModels API from OpenAI & create instance
        //You can also specify the model and use the following:
        //EditData.create(model, spellingMistake, instruction);
        EditData editData = EditData.create(spellingMistake, instruction);

        //Call the CreateEdit API from OpenAI & create instance
        CreateEdit createEdit = openAI.createEdit(editData);

        //Return model as a JSON string
        return createEdit.asText();
    }

}
