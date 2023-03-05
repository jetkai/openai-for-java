package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.GetModel;

/**
 * ExampleGetModel
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
public class ExampleGetModel {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) {
        //Initialize ExampleGetModel class
        ExampleGetModel getModel = new ExampleGetModel();

        String modelName = "davinci";

        //Send request to API - response as JSON string
        String modelJson = getModel.communicate(modelName);

        //Print out the model information in JSON
        System.out.println(modelJson);
    }

    private String communicate(String modelName) {
        //Call the GetModels API from OpenAI & create instance
        GetModel getModels = openAI.getModel(modelName);

        //Return model as a JSON string
        return getModels.asJson();
    }

}
