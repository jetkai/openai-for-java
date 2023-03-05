package io.github.jetkai.openai.net;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * HttpClientInstance
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.1
 * {@code - 05/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class HttpClientInstance {

    private HttpClient httpClient;

    public CompletableFuture<HttpResponse<String>> sendAsync(Object data,
                                                             RequestBuilder requestBuilder) {
        HttpRequest request = requestBuilder.request(data);
        if(request == null || httpClient == null) {
            return null;
        }
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler());
    }

    private HttpResponse.PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>, CompletableFuture<HttpResponse<String>>> acceptor) -> {
            acceptor.apply(HttpResponse.BodyHandlers.ofString())
                    /*.thenAccept(resp ->
                            System.out.println(" Pushed response: " + resp.uri() + ", headers: " + resp.headers()));
            System.out.println("Promise request: " + pushPromiseRequest.uri());
            System.out.println("Promise request: " + pushPromiseRequest.headers())*/;
        };
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @SuppressWarnings("unused")
    public HttpClient getHttpClient() {
        return httpClient;
    }

}
