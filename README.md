# OpenAI For Java

###### ⚠️ This library is currently under-development as the API has only been public since the 1st of March.

API Reference -> https://platform.openai.com/docs/api-reference/

## Library Downloads
- [openai-binary.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-binary.jar) - ~2.1 MB `(with dependencies)`
- [openai-binary.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-no-dependencies-binary.jar) - ~80.0 KB `(no dependencies included)`
- [openai-javadoc.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-javadoc.jar) - ~400.0 KB
- [openai-sources.jar](https://github.com/jetkai/openai-for-java/releases/download/1.0.0/openai-sources.jar) - ~50.00 KB

## Minimum Requirements
- JDK 11
- An OpenAI API Key - **You can get a free API key from** https://platform.openai.com/account/api-keys

## Available API(s)
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

## Example Usages

### GetModel

```java
@Test
    void getModelTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Set model to view
        String modelName = "davinci";

        //Call the GetModel API from OpenAI & create instance
        GetModel getModel = openAI.getModel(modelName);

        //Data structure example
        ModelData modelData = getModel.asData();
        assertNotNull(modelData);

        //Get id from data structure example
        String id = modelData.getId();
        assertEquals(id, modelName);

        //Json example
        String json = getModel.asJson();
        assertNotNull(json);
    }
```

### GetModels

```java
    @Test
    void getModelsTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Call the GetModels API from OpenAI & create instance
        GetModels getModels = openAI.getModels();

        //Data structure example
        ModelData[] modelData = getModels.asDataArray(); //You can view all the listed models here
        assertNotNull(modelData);
        assertTrue(modelData.length > 0);

        //Data list example
        List<ModelData> modelList = getModels.asDataList();
        assertNotNull(modelList);
        assertTrue(modelList.size() > 0);

        //Json example
        String json = getModels.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
```

### CreateCompletion

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

### CreateChatCompletion

```java
    @Test
    void createChatCompletionTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Create message object, this will contain the data we want to send to ChatGPT
        ChatCompletionMessageData message = new ChatCompletionMessageData();

        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        //The role of the user
        message.setRole("user");

        //Message that you would like to send to OpenAI ChatGPT
        message.setContent("Hello!");

        //Add message to the messages list
        messages.add(message);

        //Completion Data, ready to send to the OpenAI Api
        ChatCompletionData completion = new ChatCompletionData();

        //ID of the model to use. Currently, only gpt-3.5-turbo and gpt-3.5-turbo-0301 are supported.
        completion.setModel("gpt-3.5-turbo");

        //The messages to generate chat completions for, in the chat format.
        completion.setMessages(messages);

        //Call the CreateChatCompletion API from OpenAI & create instance
        CreateChatCompletion createChatCompletion = openAI.createChatCompletion(completion);

        //Data structure example
        CompletionResponseData responseData = createChatCompletion.asData();
        assertNotNull(responseData);

        //StringArray example - contains the response in plaintext from ChatGPT
        String[] stringArray = createChatCompletion.asStringArray();
        assertNotNull(stringArray);

        //Json example
        String json = createChatCompletion.asJson();
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

### CreateImage

```java
    @Test
    void createImageTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI();

        //Keep the same instance for openAI.createImage(imageData);
        openAI.setAlwaysNewInstance(false);

        //Set the API key (from .json file)
        openAI.setApiKey(apiKey);

        //Set the organization (from .json file)
        openAI.setOrganization(organization);

        //ImageData, ready to send to the OpenAI API
        ImageData imageData = new ImageData();

        //A text description of the desired image(s). The maximum length is 1000 characters
        imageData.setPrompt("A cute baby sea otter");

        //The number of images to generate. Must be between 1 and 10.
        imageData.setN(2);

        //The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
        imageData.setSize("1024x1024");

        //Call the CreateImage API from OpenAI & create instance
        CreateImage createImage = openAI.createImage(imageData);

        //Grabs the first image in the array, if your "setN" is higher than 1, then use imageArray
        Image image = createImage.asImage();
        assertNotNull(image);

        //Array of images, size depends on the "setN" value
        Image[] imageArray = createImage.asImageArray();
        assertNotNull(imageArray);
        assertNotEquals(0, imageArray.length);

        //String List example (contains all the image urls)
        List<String> imageList = createImage.asStringList();
        assertNotNull(imageList);

        //URIArray example (contains all the image urls)
        URI[] uriArray = createImage.asUriArray();
        assertNotNull(uriArray);

        //Data structure example
        ImageResponseData responseData = createImage.asData();
        assertNotNull(responseData);

        //Json example
        String json = createImage.asJson();
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
 
### CreateTranscription - (English Audio)

```java
    @Test
    void createTranscriptionTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Example audio file that we are going to upload to OpenAI to have a transcript of
        URL audioUrl = CreateImageEditTest.class.getResource("what-can-i-do.mp3");
        Path audioPath = null;
        try {
            if (audioUrl != null) {
                audioPath = Path.of(audioUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(audioPath);

        //TranscriptionData, ready to send to the OpenAI Api
        TranscriptionData transcriptionData = new TranscriptionData();

        //Set the path for the audio file
        transcriptionData.setFile(audioPath);

        //Use the whisper-1 model for translation
        transcriptionData.setModel("whisper-1");

        //Option to specify language of the audio file
        //transcriptionData.setLanguage("en");

        //Call the CreateTranscription API from OpenAI & create instance
        CreateTranscription createTranscription = openAI.createTranscription(transcriptionData);

        //Transcript as a string (Audio File -> English)
        String transcript = createTranscription.asText();
        assertNotNull(transcript);
        assertFalse(transcript.isEmpty());

        //Get id from data structure example
        TranscriptionResponseData responseData = createTranscription.asData();
        assertNotNull(responseData);

        //Json example
        String json = createTranscription.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
```
 
### CreateTranslation - (English Audio to French)

```java
    @Test
    void createTranslationTest() {
        //Grab OpenAI API key from system environment variables (gradle.properties)
        String apiKey = System.getenv("OPEN_AI_API_KEY");
        String organization = System.getenv("OPEN_AI_ORGANIZATION");
        assertNotNull(apiKey);
        assertNotNull(organization);

        //Create OpenAI instance using API key & organization
        //Organization is optional
        OpenAI openAI = new OpenAI(apiKey, organization);

        //Example audio file that we are going to upload to OpenAI to be translated
        URL audioUrl = CreateImageEditTest.class.getResource("what-can-i-do.mp3");
        Path audioPath = null;
        try {
            if (audioUrl != null) {
                audioPath = Path.of(audioUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(audioPath);

        //TranslationData, ready to send to the OpenAI API
        TranslationData translationData = new TranslationData();

        //Set the path for the audio file
        translationData.setFile(audioPath);

        //Use the whisper-1 model for translation
        translationData.setModel("whisper-1");

        //Set language to translate (French)
        translationData.setLanguage("fr");

        //Call the CreateTranslation API from OpenAI & create instance
        CreateTranslation createTranslation = openAI.createTranslation(translationData);

        //Data structure example
        TranslationResponseData responseData = createTranslation.asData();
        assertNotNull(responseData);

        //Translated text as string (English -> French)
        String translatedText = createTranslation.asText();
        assertNotNull(translatedText);
        assertFalse(translatedText.isEmpty());

        //Json example
        String json = createTranslation.asJson();
        assertNotNull(json);
        assertFalse(json.isEmpty());
    }
```
