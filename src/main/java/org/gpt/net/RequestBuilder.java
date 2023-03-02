package org.gpt.net;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public abstract class RequestBuilder {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36";

    public abstract HttpRequest request(Object data);

    public HttpRequest get(URI uri, String apiKey, String organization) {
        return HttpRequest.newBuilder()
                .headers("Content-Type", "application/json")
                .uri(uri)
                .header("User-Agent", USER_AGENT)
                .header("Authorization", "Bearer " + apiKey)
                .header("OpenAI-Organization", organization)
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
    }

    public HttpRequest post(URI uri, Object data, String apiKey, String organization) {
        return HttpRequest.newBuilder()
                .headers("Content-Type", "application/json")
                .uri(uri)
                .header("User-Agent", USER_AGENT)
                .header("Authorization", "Bearer " + apiKey)
                .header("OpenAI-Organization", organization)
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(data)))
                .build();
    }

}
