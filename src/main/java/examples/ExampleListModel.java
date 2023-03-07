package examples;

import io.github.jetkai.openai.api.ListModel;
import io.github.jetkai.openai.openai.OpenAI;

import java.util.Optional;

/**
 * ExampleListModel
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 *
 * <p>
 * Note - This is just a test class, it is recommended to import this project as a library
 * and then call OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
 * </p>
 */
public class ExampleListModel {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */

    private final OpenAI ai = OpenAI.builder()
            .setApiKey(System.getenv("OPEN_AI_API_KEY"))
            .build();

    public static void main(String[] args) {
        //Initialize ExampleListModel class
        ExampleListModel getModel = new ExampleListModel();

        //Send request to API - response as JSON string
        String modelJson = getModel.communicate();

        //Print out the model information in JSON
        System.out.println(modelJson);
    }

    private String communicate() {

        String modelName = "davinci";

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .listModel(modelName)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the ListModels API from OpenAI & create instance
        Optional<ListModel> getModels = openAI.model();

        //Return model as a JSON string
        return getModels.map(ListModel::asJson).orElse(null);
    }

}
