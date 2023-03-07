# OpenAI For Java
[![codecov](https://codecov.io/gh/jetkai/openai-for-java/branch/main/graph/badge.svg?token=2E96DBLLN0)](https://codecov.io/gh/jetkai/openai-for-java)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/jetkai/openai-for-java/main.yml)

    ⚠️ This library is currently under development ⚠️
    
###### OpenAI API Reference -> https://platform.openai.com/docs/api-reference/
###### OpenAI API Key -> https://platform.openai.com/account/api-keys

## Release `1.1.0`

#### Binary:
- [openai.jar](https://github.com/jetkai/openai-for-java/releases/download/1.1.0/openai.jar) `(with dependencies)` 
- [openai-excldeps.jar](https://github.com/jetkai/openai-for-java/releases/download/1.1.0/openai-excldeps.jar) `(excluding dependencies)`

#### Other:
- [openai-sources.jar](https://github.com/jetkai/openai-for-java/releases/download/1.1.0/openai-sources.jar)
- [openai-javadoc.jar](https://github.com/jetkai/openai-for-java/releases/download/1.1.0/openai-javadoc.jar)

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
       private String exampleBuilders(String message) {
        //Create the Message Data object with the message we wish to send
        ChatCompletionMessageData messageData = ChatCompletionMessageData.builder()
                .setRole("user")
                .setContent(message)
                .build();

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Build the data structure which contains the message history and model information
        ChatCompletionData completionData = ChatCompletionData.builder()
                .setModel("gpt-3.5-turbo")
                .setMessages(messageHistory)
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        //Instead of openAI.sendRequest(); you can initialize the request
        //for the class manually - openAI.createChatCompletion().initialize();
        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createChatCompletion(completionData)
                .build()
                .sendRequest();

        //Check to see if there is a valid response from OpenAI
        //You can also call Optional<CreateChatCompletion> createChatCompletion = openAI.chatCompletion();
        CreateChatCompletion createChatCompletion = openAI.getChatCompletion();

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        //Parse the response back as plain-text & replace \n & ascii characters (Šťŕĭńġ -> String)
        return createChatCompletion.asNormalizedText();
    }
    
    private String exampleHttpClientProxy(String message) {
        ProxySelector proxySelector = ProxySelector.of(new InetSocketAddress("1.0.205.87", 8080));

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .proxy(proxySelector)
                .connectTimeout(Duration.ofSeconds(10)) //10 seconds timeout
                .build();

        //Create the Message Data object with the message we wish to send
        ChatCompletionMessageData messageData = ChatCompletionMessageData.create(message);

        //Store the message that we want to send, to the MessageHistory List
        messageHistory.add(messageData);

        //Build the data structure which contains the message history and model information
        ChatCompletionData completionData = ChatCompletionData.create(messageHistory);

        //Sends the request to OpenAI's endpoint & parses the response data
        //Instead of openAI.sendRequest(); you can initialize the request for the class manually
        //openAI.createChatCompletion().initialize();
        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .setHttpClient(httpClient)
                .build();

        //Create instance which will return as the API class that we specified in the enum or .class
        //CreateChatCompletion createChatCompletion = openAI.createInstance(CreateChatCompletion.class, completionData);
        CreateChatCompletion createChatCompletion = openAI.createInstance(
                OpenAIEndpoints.CREATE_CHAT_COMPLETION, completionData
        );

        //Store chat response from AI, this allows the AI to see the full history of our chat
        //Including both our messages and the AI's messages
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        //Parse the response back as plain-text & replace \n & ascii characters (Šťŕĭńġ -> String)
        return createChatCompletion.asNormalizedText();
    }
```

## [AI Image Creation](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleImageCreation.java)
    Scenario: Have an AI create a red panda with glasses, and have this drawn as a cartoon.
```java
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
        ImageData imageData = ImageData.builder()
                //A text description of the desired image(s). The maximum length is 1000 characters
                .setPrompt(imageDescription)
                //The number of images to generate. Must be between 1 and 10.
                .setN(numberOfImages)
                //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
                .setSize(size)
                .build();

        OpenAI openAI = OpenAI.builder()
                //Set our OpenAI API key
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                //Specify the createImage API endpoint
                .createImage(imageData)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the CreateImage API from OpenAI & create instance
        Optional<CreateImage> createImage = openAI.image();

        //Gather the URI(s) from the API response
        return createImage.map(CreateImage::asUriArray).orElse(null);
    }
```

## [Spelling Correction](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleSpellingCorrection.java)
    Scenario: Correct the spelling within the sentence "Wha dai of the wek is it?".
```java
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
```

## [Translate Language](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleTranslation.java)
    Scenario: Translate "Hello, how are you today?" from English to French.
```java
    public static void main(String[] args) {
        //Initialize ExampleTranslation class
        ExampleTranslation translation = new ExampleTranslation();

        //This is the language we want to translate our audio file to
        String toLanguage = "French";

        //Example audio file that we are going to upload to OpenAI to be translated
        String message = "Hello, how are you today?";

        //Response from OpenAI with the translated string
        String response = translation.communicate(message, toLanguage);

        //Print the translation to the console
        System.out.println(response);
    }

    private String communicate(String message, String toLanguage) {
        //TranslationData, ready to send to the OpenAI API
        CompletionData translationData = CompletionData.translation(message, toLanguage);

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createTranslation(translationData)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the CreateTranslation API from OpenAI & create instance
        Optional<CreateTranslation> createTranslation = openAI.translation();

        //Return as text, do not replace \n or ascii characters
        return createTranslation.map(CreateCompletion::asText).orElse(null);
    }
```

## [Audio Transcript](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleTranscriptionFromAudioFile.java)
    Scenario: Create a transcript from an audio file.

```java
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

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createTranscription(transcriptionData)
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the CreateTranscription API from OpenAI & create instance
        Optional<CreateTranscription> createTranscription = openAI.transcription();

        //Return as text, do not replace \n or ascii characters
        return createTranscription.map(CreateTranscription::asText).orElse(null);
    }
```

## [Audio Transcript & Translate](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleTranslationFromAudioFile.java)
    Scenario: Create a transcript from an audio file, then translate the transcript from English to French.
    
```java
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

        OpenAI openAI = OpenAI.builder()
                .setApiKey(System.getenv("OPEN_AI_API_KEY"))
                .createTranscriptionTranslation(audioTranslationData)
                .build();

        //Sends the request to OpenAI's endpoint & parses the response data
        openAI.sendRequest();

        //Call the CreateTranslation API from OpenAI & create instance
        Optional<CreateTranscriptionTranslation> createTranslation = openAI.transcriptionTranslation();

        //Return as text, do not replace \n or ascii characters
        return createTranslation.map(CreateTranscription::asText).orElse(null);
    }
```

## [Get Model Information](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleGetModel.java)
    Scenario: Get information about a specific model.

```java
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
```

## [List All Models](https://github.com/jetkai/openai-for-java/blob/main/src/main/java/examples/ExampleGetModels.java)
    Scenario: List all the models available from OpenAI.
```java
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
                .build()
                //Sends the request to OpenAI's endpoint & parses the response data
                .sendRequest();

        //Call the ListModels API from OpenAI & create instance
        Optional<ListModels> getModels = openAI.models();

        //Return models as a data structure list, so that we can get the name from each model
        return getModels.map(ListModels::asDataList).orElse(null);
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

        //CompletionData, ready to send to the OpenAI API
        CompletionData completion = CompletionData.builder()
                //ID of the model to use. You can use the List models API to see all of your available models,
                //or see our Model overview for descriptions of them.
                .setModel("text-davinci-003")

                //The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens,
                //or array of token arrays.
                //Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt
                //is not specified the model will generate as if from the beginning of a new document.
                .setPrompt("Say this is a test")

                //The maximum number of tokens to generate in the completion.
                //The token count of your prompt plus max_tokens cannot exceed the model's context length.
                //Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
                .setMaxTokens(7)

                //What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the
                //output more random, while lower values like 0.2 will make it more focused and deterministic.
                //We generally recommend altering this or top_p but not both.
                .setTemperature(0)

                //An alternative to sampling with temperature, called nucleus sampling, where the model considers
                //the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the
                //top 10% probability mass are considered.
                //We generally recommend altering this or temperature but not both.
                .setTopP(1)

                //How many completions to generate for each prompt.
                //Note: Because this parameter generates many completions, it can quickly consume your token quota.
                //Use carefully and ensure that you have reasonable settings for max_tokens and stop.
                .setN(1)

                //Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent
                //events as they become available, with the stream terminated by a data: [DONE] message.
                .setStream(false)

                //Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
                //For example, if logprobs is 5, the API will return a list of the 5 most likely tokens.
                //The API will always return the logprob of the sampled token, so there may be up to logprobs+1
                //elements in the response.
                //The maximum value for logprobs is 5. If you need more than this, please contact us through our
                //Help center and describe your use case.
                //.setLogprobs()

                //Up to 4 sequences where the API will stop generating further tokens.
                //The returned text will not contain the stop sequence.
                .setStop("\n")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createCompletion(completion)
                .build()
                .sendRequest();

        assertNotNull(openAI);

        //Call the CreateCompletion API from OpenAI & create instance
        Optional<CreateCompletion> optionalCreateCompletion = openAI.completion();
        assertFalse(optionalCreateCompletion.isEmpty());

        CreateCompletion createCompletion = optionalCreateCompletion.get();

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

        //EditData, ready to send to the OpenAI Api
        EditData edit = EditData.builder()
                //ID of the model to use. You can use the text-davinci-edit-001 or
                //code-davinci-edit-001 model with this endpoint.
                .setModel("text-davinci-edit-001")

                //The input text to use as a starting point for the edit.
                .setInput("What day of the wek is it?")

                //The instruction that tells the model how to edit the prompt.
                .setInstruction("Fix the spelling mistakes")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createEdit(edit)
                .build()
                .sendRequest();

        assertNotNull(openAI);

        //Call the CreateEdit API from OpenAI & create instance
        Optional<CreateEdit> optionalCreateEdit = openAI.edit(); //You can call "data" to see the response
        assertFalse(optionalCreateEdit.isEmpty());

        CreateEdit createEdit = optionalCreateEdit.get();

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
        ImageVariationData imageVariationData = ImageVariationData.builder()
                //Set the path for the image file
                .setImage(imagePath)
                //Set the number of images to generate. Must be between 1 and 10.
                .setN(2)
                //Set the size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
                .setSize("256x256")
                .build();

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createImageVariation(imageVariationData)
                .build()
                //Finally, send our request to the API, this initiates the request (after .build())
                .sendRequest();


        //Call the CreateTranscription API from OpenAI & create instance
        Optional<CreateImageVariation> optionalCreateImageVariation = openAI.imageVariation();
        assertFalse(optionalCreateImageVariation.isEmpty());

        CreateImageVariation createImageVariation = optionalCreateImageVariation.get();

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

        //EmbeddingData, ready to send to the OpenAI API
        EmbeddingData embed = EmbeddingData.builder()
                //ID of the model to use. You can use the List models API to see all of your available models,
                //or see our Model overview for descriptions of them.
                .setModel("text-embedding-ada-002")
                //Input text to get embeddings for, encoded as a string or array of tokens.
                //To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
                //Each input must not exceed 8192 tokens in length.
                .setInput("The food was delicious and the waiter...")
                .build();

        //Call the CreateEmbedding API from OpenAI & create instance
        OpenAI openAI = OpenAI.builder()
                .setApiKey(apiKey)
                .setOrganization(organization)
                .createEmbedding(embed)
                .build()
                .sendRequest();

        Optional<CreateEmbedding> optionalEmbedding = openAI.embedding();
        assertFalse(optionalEmbedding.isEmpty());

        CreateEmbedding createEmbedding = optionalEmbedding.get();

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
