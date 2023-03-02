package org.gpt.net;

import java.net.URI;

public enum ChatGPTEndpoints {

    GET_MODEL_URI(URI.create("https://api.openai.com/v1/models")),
    GET_MODELS_URI(URI.create("https://api.openai.com/v1/models")),
    CREATE_COMPLETION_URI(URI.create("https://api.openai.com/v1/completions")),
    CREATE_CHAT_COMPLETION_URI(URI.create("https://api.openai.com/v1/chat/completions")),
    CREATE_EDIT_URI(URI.create("https://api.openai.com/v1/edits")),
    CREATE_IMAGE_URI(URI.create("https://api.openai.com/v1/images/generations")),
    CREATE_IMAGE_EDIT_URI(URI.create("https://api.openai.com/v1/images/edits"));

    private final URI uri;

    ChatGPTEndpoints(URI uri) {
        this.uri = uri;
    }

    public URI uri() {
        return uri;
    }

}
