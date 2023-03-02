# OpenAI For Java

###### ⚠️ This library is currently under-development as the API has only been public since the 1st of March.

API Reference -> https://platform.openai.com/docs/api-reference/

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
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        String modelName = "davinci";
        ModelData data = openAI.getModel(modelName); //You can view the listed model here

        assertEquals(data.getId(), modelName);
    }
```

### GetModels

```java
    @Test
    void getModelsTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        ModelData[] data = openAI.getModels(); //You can view all the listed models here

        assertTrue(data.length > 1);
    }
```

### CreateCompletion

```java
    @Test
    void createCompletionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        CompletionData completion = new CompletionData();
        completion.setModel("text-davinci-003");
        completion.setPrompt("Say this is a test");
        completion.setMaxTokens(7);
        completion.setTemperature(0);
        completion.setTopP(1);
        completion.setN(1);
        completion.setStream(false);
        completion.setLogprobs(null);
        completion.setStop("\n");

        CompletionResponseData data = openAI.createCompletion(completion); //You can call "data" to see the response

        assertNotNull(data.getModel());
    }
```

### CreateChatCompletion

```java
    @Test
    void createChatCompletionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Object of the Message it-self
        ChatCompletionMessageData message = new ChatCompletionMessageData();
        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        message.setRole("user");
        message.setContent("Hello!");

        messages.add(message);

        //Completion Data, ready to send to the OpenAI Api
        ChatCompletionData completion = new ChatCompletionData();
        completion.setModel("gpt-3.5-turbo");
        completion.setMessages(messages);

        CompletionResponseData data = openAI.createChatCompletionResponse(completion); //You can call "data" to see the response

        assertNotNull(data.getModel());
    }
```

### CreateEdit

```java
    @Test
    void createEditTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        EditData edit = new EditData();
        edit.setModel("text-davinci-edit-001");
        edit.setInput("What day of the wek is it?");
        edit.setInstruction("Fix the spelling mistakes");

        CompletionResponseData data = openAI.createEditResponse(edit); //You can call "data" to see the response

        assertFalse(data.getChoices().isEmpty());
    }
```

### CreateImage
```java
    @Test
    void createImageTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        ImageResponseData data = openAI.createImageResponse(image); //You can call "data" to see the imagesUrls

        assertFalse(data.getData().isEmpty());
    }
```

### CreateImage - Return as ImageArray
```java
    @Test
    void createImageAwtTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageData image = new ImageData();
        image.setPrompt("A cute baby sea otter");
        image.setN(2);
        image.setSize("1024x1024");

        Image[] images = openAI.createImage(image);
        assertNotNull(images);
        assertNotEquals(0, images.length);
    }
```

### CreateImageEdit
```java
    @Test
    void createEditTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        EditData edit = new EditData();
        edit.setModel("text-davinci-edit-001");
        edit.setInput("What day of the wek is it?");
        edit.setInstruction("Fix the spelling mistakes");

        CompletionResponseData data = openAI.createEditResponse(edit); //You can call "data" to see the response

        assertFalse(data.getChoices().isEmpty());
    }
```
 
 ### CreateImageVariation
```java
    @Test
    void createImageVariationTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        ImageVariationData image = new ImageVariationData();

        Path imagePath = null;
        URL imageUrl = CreateImageVariationTest.class.getResource("otter.png");
        try {
            if (imageUrl != null) {
                imagePath = Path.of(imageUrl.toURI());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(imagePath);

        image.setImage(imagePath);

        image.setN(2);
        image.setSize("1024x1024");

        ImageResponseData data = openAI.createImageVariationResponse(image);

        assertFalse(data.getData().isEmpty());
    }
```

### CreateEmbedding
```java
    @Test
    void createEmbeddingTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        EmbeddingData embed = new EmbeddingData();
        embed.setModel("text-embedding-ada-002");
        embed.setInput("The food was delicious and the waiter...");

        EmbeddingResponseData data = openAI.createEmbeddingResponse(embed);

        List<EmbeddingResponseDataBlock> embeddingBlock = data.getData();

        assertNotNull(embeddingBlock);

        assertFalse(embeddingBlock.isEmpty());
        assertFalse(embeddingBlock.get(0).getEmbedding().isEmpty());
    }
 ```
 
 ## CreateTranscription - (English Audio)
 ```java
    @Test
    void createTranscriptionTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        TranscriptionData transcript = new TranscriptionData();

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

        transcript.setFile(audioPath);
        transcript.setModel("whisper-1");

        String data = openAI.createTranscription(transcript);

        assertNotNull(data);
        assertFalse(data.isEmpty());
    }
 ```
 
 ### CreateTranslation - (English Audio to French)
 
 ```java
    @Test
    void createTranslationTest() {
        ApiKeyFileData keyData = getApiKeyFromFile();

        assertNotNull(keyData);

        OpenAI openAI = new OpenAI(keyData.getApiKey(), keyData.getOrganization());

        //Completion Data, ready to send to the OpenAI Api
        TranslationData translation = new TranslationData();

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

        translation.setFile(audioPath);
        translation.setModel("whisper-1");
        translation.setLanguage("fr");

        String data = openAI.createTranslation(translation);

        assertNotNull(data);
        assertFalse(data.isEmpty());
    }
```
