package io.github.jetkai.openai.net;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class HttpClientInstance {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .connectTimeout(Duration.ofSeconds(15)) //15 seconds timeout
            .build();

    public CompletableFuture<HttpResponse<String>> getResponse(Object data, RequestBuilder requestBuilder) {
        HttpRequest request = requestBuilder.request(data);
        if(request == null) {
            System.err.println("Unable to request data: " + data);
            return null;
        }
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler());
    }

    private static HttpResponse.PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>, CompletableFuture<HttpResponse<String>>> acceptor) -> {
            acceptor.apply(HttpResponse.BodyHandlers.ofString())
                    /*.thenAccept(resp ->
                            System.out.println(" Pushed response: " + resp.uri() + ", headers: " + resp.headers()));
            System.out.println("Promise request: " + pushPromiseRequest.uri());
            System.out.println("Promise request: " + pushPromiseRequest.headers())*/;
        };
    }

}
