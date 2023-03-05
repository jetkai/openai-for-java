package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.GetModels;
import io.github.jetkai.openai.api.data.model.ModelData;

import java.util.List;

/**
 * ExampleGetModels
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
public class ExampleGetModels {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) {
        //Initialize ExampleGetModels class
        ExampleGetModels getModels = new ExampleGetModels();

        //Send request to API and deserialize response as List<ModalData>
        List<ModelData> models = getModels.communicate();

        //Print out the names of all the available models, to the console
        models.stream().map(ModelData::getId).forEach(System.out::println);
    }

    private List<ModelData> communicate() {
        //Call the GetModels API from OpenAI & create instance
        GetModels getModels = openAI.getModels();

        //Return models as a data structure list, so that we can get the name from each model
        return getModels.asDataList();
    }

}
