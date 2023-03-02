package org.gpt.net;

import java.net.URI;

public enum ChatGPTURI {

    GET_MODEL_URI(URI.create("https://api.openai.com/v1/models")),
    GET_MODELS_URI(URI.create("https://api.openai.com/v1/models")),
    CREATE_COMPLETION_URI(URI.create("https://api.openai.com/v1/completions")),
    CREATE_CHAT_COMPLETION_URI(URI.create("https://api.openai.com/v1/chat/completions"));

    private final URI uri;

    ChatGPTURI(URI uri) {
        this.uri = uri;
    }

    public URI uri() {
        return uri;
    }

}
