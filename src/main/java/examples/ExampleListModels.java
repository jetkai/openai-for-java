package examples;

import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.ListModels;
import io.github.jetkai.openai.api.data.model.ModelData;

import java.util.List;
import java.util.Optional;

/**
 * ExampleListModels
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
public class ExampleListModels {

    /*
     * You can get a free API key from https://platform.openai.com/account/api-keys
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY");
     * private final OpenAI openAI = new OpenAI("YOUR_API_KEY", "YOUR_ORGANIZATION");
     */
    //private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) {
        //Initialize ExampleListModels class
        ExampleListModels getModels = new ExampleListModels();

        //Send request to API and deserialize response as List<ModalData>
        List<ModelData> models = getModels.communicate();
        for(ModelData model : models) {
            Optional<String> id = model.id();
            if(id.isEmpty()) {
                continue;
            }
            //Print out the names of all the available models, to the console
            System.out.println(id.get());
        }
        //models.stream().map(ModelData::id).forEach(System.out::println);
    }

    private List<ModelData> communicate() {
        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .listModels()
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        openAI.sendRequest();

        //Call the ListModels API from OpenAI & create instance
        Optional<ListModels> getModels = openAI.models();

        //Return models as a data structure list, so that we can get the name from each model
        return getModels.map(ListModels::asDataList).orElse(null);
    }

}
