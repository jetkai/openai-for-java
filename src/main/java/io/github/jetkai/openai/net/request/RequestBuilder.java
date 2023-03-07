package io.github.jetkai.openai.net.request;

import io.github.jetkai.openai.api.data.audio.AudioData;
import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * RequestBuilder
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public abstract class RequestBuilder {

    private static final Map<Class<?>, RequestDataHandler> handlers = new HashMap<>();

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36";

    static {
        handlers.put(AudioData.class, new AudioDataHandler());
        handlers.put(ImageVariationData.class, new ImageVariationDataHandler());
        handlers.put(ImageEditData.class, new ImageEditDataHandler());
    }

    public abstract HttpRequest request(Object data);

    public HttpRequest createGetRequest(URI uri, String apiKey, String organization) {
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

    public HttpRequest createPostRequest(URI uri, Object data, String apiKey, String organization) {
        Object postData = data == null ? "" : data;
        return HttpRequest.newBuilder()
                .headers("Content-Type", "application/json")
                .uri(uri)
                .header("User-Agent", USER_AGENT)
                .header("Authorization", "Bearer " + apiKey)
                .header("OpenAI-Organization", organization)
                .version(HttpClient.Version.HTTP_2)
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(postData)))
                .build();
    }

    public HttpRequest createMultiDataPost(URI uri, Object data, String apiKey, String organization) {
        Map<Object, Object> map = new LinkedHashMap<>();
        RequestDataHandler handler = handlers.get(data.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("Unhandled data type: " + data.getClass().getName());
        }
        handler.populateMap(data, map);

        String boundary = new BigInteger(256, new Random()).toString();
        return HttpRequest.newBuilder()
                .headers("Content-Type", "multipart/form-data; boundary=" + boundary)
                .uri(uri)
                .header("User-Agent", USER_AGENT)
                .header("Authorization", "Bearer " + apiKey)
                .header("OpenAI-Organization", organization)
                .version(HttpClient.Version.HTTP_2)
                .POST(ofMimeMultipartData(map, boundary))
                .build();
    }

    private HttpRequest.BodyPublisher ofMimeMultipartData(Map<Object, Object> data, String boundary) {
        List<byte[]> byteArrays = new ArrayList<>();
        try {
            String separator = "--" + boundary + "\r\nContent-Disposition: form-data; name=";
            byte[] separatorBytes = separator.getBytes(StandardCharsets.UTF_8);

            for (Map.Entry<Object, Object> entry : data.entrySet()) {
                byteArrays.add(separatorBytes);
                Object value = entry.getValue();
                if (value instanceof Path) {
                    Path path = (Path) value;
                    String mimeType = Files.probeContentType(path);
                    byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                            + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n")
                            .getBytes(StandardCharsets.UTF_8));
                    byteArrays.add(Files.readAllBytes(path));
                } else {
                    byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + value + "\r\n")
                            .getBytes(StandardCharsets.UTF_8));
                }
            }

            byteArrays.add(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException io) {
            io.printStackTrace();
        }
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

}

