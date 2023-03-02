# ChatGPT For Java

ChatGPT library for Java - Requires JDK 11 at minimum.

###### ⚠️ This library is currently under-development as the API has only been public since the 1st of March.

API Reference -> https://platform.openai.com/docs/api-reference/

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

## Example Usage

Input Data:

```json
{
  "model": "gpt-3.5-turbo",
  "messages": [{"role": "user", "content": "Hello!"}]
}
```

Response:

```json
{
  "id": "chatcmpl-123",
  "object": "chat.completion",
  "created": 1677652288,
  "choices": [{
    "index": 0,
    "message": {
      "role": "assistant",
      "content": "\n\nHello there, how may I assist you today?",
    },
    "finish_reason": "stop"
  }],
  "usage": {
    "prompt_tokens": 9,
    "completion_tokens": 12,
    "total_tokens": 21
  }
}
```

[Source Code - ChatGPT Chat API:](https://github.com/jetkai/chatgpt-for-java/blob/main/src/test/java/CreateChatCompletionTest.java)
```java
    public String sayHelloToChatGPT() {

        ApiKeyFileData keyData = getApiKeyFromFile();

        if(keyData == null) {
            return "Error - Could not find API Key within \"resources/org/gpt/util/ChatGPT-API-Key.json\"";
        }

        /*
         * You can manually type in the API Key instead of loading from the file:
         * ChatGPT gpt = new ChatGPT("MY_API_KEY");
         * ChatGPT gpt = new ChatGPT("MY_API_KEY", "MY_ORGANIZATION");
         */
        ChatGPT gpt = new ChatGPT(keyData.getApiKey(), keyData.getOrganization());

        //Object of the Message it-self
        ChatCompletionMessageData message = new ChatCompletionMessageData();
        //List of Messages that you would like to send to the Chat Bot
        List<ChatCompletionMessageData> messages = new ArrayList<>();

        message.setRole("user");
        message.setContent("Hello!");

        messages.add(message);

        //Completion Data, ready to send to the ChatGPT Api
        ChatCompletionData completion = new ChatCompletionData();
        completion.setModel("gpt-3.5-turbo");
        completion.setMessages(messages);

        String[] response = gpt.createChatCompletion(completion);

        if(response != null && response.length > 0) {
            return Arrays.toString(response);
        }

        return "Error - No choice found.";

    }
```
