package org.gpt.net;

import org.gpt.api.CreateImageEdit;
import org.gpt.api.CreateImageVariation;
import org.gpt.api.data.image.edit.ImageEditData;
import org.gpt.api.data.image.variation.ImageVariationData;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

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

    public HttpRequest postMultiPartFormData(URI uri, Object data, String apiKey, String organization) {
        Map<Object, Object> map = new LinkedHashMap<>();
        if(data instanceof ImageEditData) {
            CreateImageEdit.populateMap((ImageEditData) data, map);
        }
        if(data instanceof ImageVariationData) {
            CreateImageVariation.populateMap((ImageVariationData) data, map);
        }
        if(map.isEmpty()) {
            System.err.println("Map for the MultiPartFormData is empty.");
            return null;
        }
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
        var byteArrays = new ArrayList<byte[]>();
        try {
            byte[] separator = ("--" + boundary + "\r\nContent-Disposition: form-data; name=")
                    .getBytes(StandardCharsets.UTF_8);
            for (Map.Entry<Object, Object> entry : data.entrySet()) {
                byteArrays.add(separator);
                if (entry.getValue() instanceof Path) {
                    Path path = (Path) entry.getValue();
                    String mimeType = Files.probeContentType(path);
                    byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                            + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n")
                            .getBytes(StandardCharsets.UTF_8));
                    byteArrays.add(Files.readAllBytes(path));
                    byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
                } else {
                    byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n").getBytes(StandardCharsets.UTF_8));
                }
            }
            byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
        } catch (IOException io) {
            io.printStackTrace();
        }
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }


}
