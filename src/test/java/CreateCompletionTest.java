import io.github.jetkai.openai.OpenAI;
import io.github.jetkai.openai.api.CreateCompletion;
import io.github.jetkai.openai.api.data.completion.CompletionData;
import io.github.jetkai.openai.api.data.completion.response.CompletionResponseData;
import io.github.jetkai.openai.util.ApiKeyFileData;
import org.junit.jupiter.api.Test;

import static io.github.jetkai.openai.util.ReadApiKeyFromFile.getApiKeyFromFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * CreateCompletionTest
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public class CreateCompletionTest {


    @Test
    void createCompletionTest() {
        //Grab API Key from .json file
        ApiKeyFileData keyData = getApiKeyFromFile();
        assertNotNull(keyData);

        //Create OpenAI instance using API key loaded from the .json file (example)
        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //CompletionData, ready to send to the OpenAI API
        CompletionData completion = new CompletionData();

        //ID of the model to use. You can use the List models API to see all of your available models,
        //or see our Model overview for descriptions of them.
        completion.setModel("text-davinci-003");

        //The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens,
        //or array of token arrays.
        //Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt
        //is not specified the model will generate as if from the beginning of a new document.
        completion.setPrompt("Say this is a test");

        //The maximum number of tokens to generate in the completion.
        //The token count of your prompt plus max_tokens cannot exceed the model's context length.
        //Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
        completion.setMaxTokens(7);

        //What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the
        //output more random, while lower values like 0.2 will make it more focused and deterministic.
        //We generally recommend altering this or top_p but not both.
        completion.setTemperature(0);

        //An alternative to sampling with temperature, called nucleus sampling, where the model considers
        //the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the
        //top 10% probability mass are considered.
        //We generally recommend altering this or temperature but not both.
        completion.setTopP(1);

        //How many completions to generate for each prompt.
        //Note: Because this parameter generates many completions, it can quickly consume your token quota.
        //Use carefully and ensure that you have reasonable settings for max_tokens and stop.
        completion.setN(1);

        //Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent
        //events as they become available, with the stream terminated by a data: [DONE] message.
        completion.setStream(false);


        //Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
        //For example, if logprobs is 5, the API will return a list of the 5 most likely tokens.
        //The API will always return the logprob of the sampled token, so there may be up to logprobs+1
        //elements in the response.
        //The maximum value for logprobs is 5. If you need more than this, please contact us through our
        //Help center and describe your use case.
        completion.setLogprobs(null);

        //Up to 4 sequences where the API will stop generating further tokens.
        //The returned text will not contain the stop sequence.
        completion.setStop("\n");

        //Call the CreateCompletion API from OpenAI & create instance
        CreateCompletion createCompletion = openAI.createCompletion(completion);

        //Data structure example
        CompletionResponseData responseData = createCompletion.asData();
        assertNotNull(responseData);

        //Json example
        String json = createCompletion.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

}
