package io.github.jetkai.openai.net;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
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

    public static final HttpClient DEFAULT_HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .connectTimeout(Duration.ofSeconds(30)) //30 seconds timeout
            .build();

    public static HttpClient customHttpClient(String proxyIp, int proxyPort, Duration timeout) {
        ProxySelector proxySelector = ProxySelector.of(new InetSocketAddress(proxyIp, proxyPort));
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .proxy(proxySelector)
                .connectTimeout(timeout) //30 seconds timeout
                .build();
    }

    private final HttpClient httpClient;

    public HttpClientInstance(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CompletionStage<HttpResponse<String>> sendAsync(Object data, RequestBuilder requestBuilder) {
        HttpRequest request = requestBuilder.request(data);
        if(request == null || httpClient == null) {
            return null;
        }
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler());
    }

    private HttpResponse.PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>, CompletableFuture<HttpResponse<String>>> acceptor)
                -> acceptor.apply(HttpResponse.BodyHandlers.ofString());
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

}
