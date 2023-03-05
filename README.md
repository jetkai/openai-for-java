# OpenAI For Java

    ⚠️ This library is currently under development ⚠️
    
###### OpenAI API Reference -> https://platform.openai.com/docs/api-reference/
###### OpenAI API Key -> https://platform.openai.com/account/api-keys

## Release `1.0.0`

#### Binary:
- [openai.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai.jar) `(with dependencies)` 
- [openai-excldeps.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-excldeps.jar) `(excluding dependencies)`

#### Other:
- [openai-sources.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-sources.jar)
- [openai-javadoc.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-javadoc.jar)

## Minimum Requirements
- JDK 11
- An OpenAI [API Key](https://platform.openai.com/account/api-keys)

## Available API(s) `March 2023`
- [GetModel](https://platform.openai.com/docs/api-reference/models/retrieve) -> `https://api.openai.com/v1/models`
- [GetModels](https://platform.openai.com/docs/api-reference/models/list) -> `https://api.openai.com/v1/models/{model}`
- [CreateCompletion](https://platform.openai.com/docs/api-reference/completions/create) -> `https://api.openai.com/v1/completions`
- [CreateChatCompletion](https://platform.openai.com/docs/api-reference/chat/create) -> `https://api.openai.com/v1/chat/completions`
- [CreateEdit](https://platform.openai.com/docs/api-reference/edits/create) -> `https://api.openai.com/v1/edits`
- [CreateImage](https://platform.openai.com/docs/api-reference/images/create) -> `https://api.openai.com/v1/images/generations`
- [CreateImageEdit](https://platform.openai.com/docs/api-reference/images/create-edit) -> `https://api.openai.com/v1/images/edits`
- [CreateImageVariation](https://platform.openai.com/docs/api-reference/images/create-variation) -> `https://api.openai.com/v1/images/variations`
- [CreateEmbeddings](https://platform.openai.com/docs/api-reference/embeddings/create) -> `https://api.openai.com/v1/embeddings`
- [CreateTranscription](https://platform.openai.com/docs/api-reference/audio/create) -> `https://api.openai.com/v1/audio/transcriptions`
- [CreateTranslation](https://platform.openai.com/docs/api-reference/audio/create) -> `https://api.openai.com/v1/audio/translations`

# Source Code (Examples)

## [ChatGPT-3.5 Turbo](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleChatGPT.java)
    Scenario: Say "Hello" to the AI and ask the AI to respond back with the last thing I said.
```java
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    //This is a List that will store all our conversation history
    //This includes our chat history and the AI's
    private final List<ChatCompletionMessageData> messageHistory = new ArrayList<>();

    public static void main(String[] args) {
        ExampleChatGPT gpt = new ExampleChatGPT();

        //The first message that we want to send
        String message1 = "Hello ChatGPT!";
        //The second message that we want to send
        String message2 = "What was the last thing I just said?";

        //Response 1 from ChatGPT
        String response1 = gpt.communicate(message1);
        System.out.println("Sent: " + message1);
        System.out.println("Response: " + response1);

        //Response 2 from ChatGPT
        String response2 = gpt.communicate(message2);
        System.out.println("Sent: " + message2);
        System.out.println("Response: " + response2);
    }

    private String communicate(String message) {
        //Create the Message Data object with the message we wish to send
        ChatCompletionMessageData messageData = ChatCompletionMessageData.create(message);

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Send the request to OpenAI, along with the MessageHistory data
        ChatCompletionData completionData = ChatCompletionData.create(messageHistory);

        //Send the request to OpenAI, along with the MessageHistory data
        CreateChatCompletion response = openAI.createChatCompletion(completionData);

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(response.asChatResponseDataList());

        //Replace \n & ascii characters (Šťŕĭńġ -> String)
        return response.asNormalizedText();
    }
```

## [AI Image Creation](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleImageCreation.java)
    Scenario: Have an AI create a red panda with glasses, and have this drawn as a cartoon.
```java
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) throws IOException {
        //Initialize ExampleImageCreation class
        ExampleImageCreation imageCreation = new ExampleImageCreation();

        //A text description of the desired image(s). The maximum length is 1000 characters
        String description = "A picture of a red panda with glasses, drawn as a cartoon.";

        //The number of images to generate. Must be between 1 and 10.
        int amount = 1;

        //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
        String size = "1024x1024";

        URI[] imageLinks = imageCreation.communicate(description, amount, size);

        for(URI imageLink : imageLinks) {
            System.out.println("Opening URI ["+imageLink+"] in the web browser.");
            Desktop.getDesktop().browse(imageLink);
        }
    }

    private URI[] communicate(String imageDescription, int numberOfImages, String size) {
        //Alternatively can use ImageData.create(imageDescription, numberOfImages, size);
        ImageData imageData = ImageData.create()
                //A text description of the desired image(s). The maximum length is 1000 characters
                .setPrompt(imageDescription)
                //The number of images to generate. Must be between 1 and 10.
                .setN(numberOfImages)
                //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
                .setSize(size);

        //Call the CreateImage API from OpenAI & create instance
        CreateImage createImage = openAI.createImage(imageData);

        //Gather the URI(s) from the API response
        return createImage.asUriArray();
    }
```

## [Spelling Correction](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleSpellingCorrection.java)
    Scenario: Correct the spelling within the sentence "Wha dai of the wek is it?".
```java
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
```

## [Translate Language](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleTranslation.java)
    Scenario: Translate "Hello, how are you today?" from English to French.
```java
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) {
        //Initialize ExampleTranslationFromAudioFile class
        ExampleTranslation translateFromAudioFile = new ExampleTranslation();

        //This is the language we want to translate our audio file to
        String toLanguage = "French";

        //Example audio file that we are going to upload to OpenAI to be translated
        String message = "Hello, how are you today?";

        //Response from OpenAI with the translated string
        String response = translateFromAudioFile.communicate(message, toLanguage);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(String message, String toLanguage) {
        //TranslationData, ready to send to the OpenAI API
        TranslationData audioTranslationData = TranslationData.simplified(message, toLanguage);

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranslation createTranslation = openAI.createTranslation(audioTranslationData);

        //Return as text, do not replace \n or ascii characters
        return createTranslation.asText();
    }
```

## [Audio Transcript](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleTranscriptionFromAudioFile.java)
    Scenario: Create a transcript from an audio file.

```java
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) throws URISyntaxException {
        //Initialize ExampleTranslationFromAudioFile class
        ExampleTranscriptionFromAudioFile transcriptAudioFile = new ExampleTranscriptionFromAudioFile();

        //Example audio file that we are going to upload to OpenAI to be translated
        URL audioUrl = ExampleTranscriptionFromAudioFile.class.getResource("what-can-i-do.mp3");
        Path filePath = null;
        if (audioUrl != null) {
            filePath = Path.of(audioUrl.toURI());
        }

        //Response from OpenAI with the translated string
        String response = transcriptAudioFile.communicate(filePath);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(Path filePath) {
        //AudioData, ready to send to the OpenAI API
        AudioData transcriptionData = AudioData.create(filePath);

        //Call the CreateTranscription API from OpenAI & create instance
        CreateTranscription createTranslation = openAI.createTranscription(transcriptionData);

        //Return as text, do not replace \n or ascii characters
        return createTranslation.asText();
    }
```

## [Audio Transcript & Translate](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleTranslationFromAudioFile.java)
    Scenario: Create a transcript from an audio file, then translate the transcript from English to French.
    
```java
    private final OpenAI openAI = new OpenAI(System.getenv("OPEN_AI_API_KEY"));

    public static void main(String[] args) throws URISyntaxException {
        //Initialize ExampleTranslationFromAudioFile class
        ExampleTranslationFromAudioFile translateFromAudioFile = new ExampleTranslationFromAudioFile();

        //This is the language we want to translate our audio file to
        //Language parameter must be specified in ISO-639-1 format or Locale
        //Example -> "French" = "fr" | "English UK" = "en_GB"
        //String language = "fr";
        Locale language = Locale.FRENCH;

        //Example audio file that we are going to upload to OpenAI to be translated
        URL audioUrl = ExampleTranslationFromAudioFile.class.getResource("what-can-i-do.mp3");
        Path filePath = null;
        if (audioUrl != null) {
            filePath = Path.of(audioUrl.toURI());
        }
        if(filePath == null) {
            System.err.println("File could not be found.");
            return;
        }

        //Response from OpenAI with the translated string
        String response = translateFromAudioFile.communicate(filePath, language);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(Path filePath, Locale language) {
        //Alternatively can use AudioData.create(audioPath, "fr");
        AudioData audioTranslationData = AudioData.create(filePath, language);

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranscriptionTranslation createTranslation = openAI.createTranscriptionTranslation(audioTranslationData);

        //Return as text, do not replace \n or ascii characters
        return createTranslation.asText();
    }
```

## [Get Model Information](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleGetModel.java)
    Scenario: Get information about a specific model.

```java
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
```

## [List All Models](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleGetModels.java)
    Scenario: List all the models available from OpenAI.
```java
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
```

## CreateCompletion

```java
    @Test
    void createCompletionTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

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
```

### CreateEdit

```java
    @Test
    void createEditTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //EditData, ready to send to the OpenAI Api
        EditData edit = new EditData();

        //ID of the model to use. You can use the text-davinci-edit-001 or
        //code-davinci-edit-001 model with this endpoint.
        edit.setModel("text-davinci-edit-001");

        //The input text to use as a starting point for the edit.
        edit.setInput("What day of the wek is it?");

        //The instruction that tells the model how to edit the prompt.
        edit.setInstruction("Fix the spelling mistakes");

        //Call the CreateEdit API from OpenAI & create instance
        CreateEdit createEdit = openAI.createEdit(edit); //You can call "data" to see the response

        //Data structure example
        CompletionResponseData responseData = createEdit.asData();
        assertNotNull(responseData);

        //StringArray example
        String[] stringArray = createEdit.asStringArray();
        assertNotNull(stringArray);

        //Json example
        String json = createEdit.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
```
 
### CreateImageVariation

```java
    @Test
    void createImageVariationTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Example image file that we are going to upload to OpenAI
        URL imageUrl = CreateImageVariationTest.class.getResource("otter.png");
        Path imagePath = null;
        try {
            if (imageUrl != null) {
                imagePath = Path.of(imageUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(imagePath);

        //ImageVariationData, ready to send to the OpenAI API
        ImageVariationData image = new ImageVariationData();

        //Set the path for the image file
        image.setImage(imagePath);

        //Set the number of images to generate. Must be between 1 and 10.
        image.setN(2);

        //Set the size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
        image.setSize("1024x1024");

        //Call the CreateTranscription API from OpenAI & create instance
        CreateImageVariation createImageVariation = openAI.createImageVariation(image);

        //String List example (contains all the image urls)
        List<String> stringList = createImageVariation.asStringList();
        assertNotNull(stringList);
        assertFalse(stringList.isEmpty());

        //URI array example (contains all the image urls)
        URI[] uriArray = createImageVariation.asUriArray();
        assertNotNull(uriArray);
        assertNotEquals(0, uriArray.length);

        //Data structure example
        ImageResponseData responseData = createImageVariation.asData();
        assertNotNull(responseData);

        //Json example
        String json = createImageVariation.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
```

### CreateEmbedding

```java
    @Test
    void createEmbeddingTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //EmbeddingData, ready to send to the OpenAI API
        EmbeddingData embed = new EmbeddingData();

        //ID of the model to use. You can use the List models API to see all of your available models,
        //or see our Model overview for descriptions of them.
        embed.setModel("text-embedding-ada-002");

        //Input text to get embeddings for, encoded as a string or array of tokens.
        //To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
        //Each input must not exceed 8192 tokens in length.
        embed.setInput("The food was delicious and the waiter...");

        //Call the CreateEmbedding API from OpenAI & create instance
        CreateEmbedding createEmbedding = openAI.createEmbedding(embed);

        //Data structure example
        EmbeddingResponseData embeddingBlock = createEmbedding.asData();
        assertNotNull(embeddingBlock);

        //Float List example
        List<Float> floatList = createEmbedding.asFloatList();
        assertNotNull(floatList);
        assertFalse(floatList.isEmpty());

        //Json example
        String json = createEmbedding.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
 ```
