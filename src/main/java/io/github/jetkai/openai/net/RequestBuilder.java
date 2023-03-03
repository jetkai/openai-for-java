package io.github.jetkai.openai.net;

import io.github.jetkai.openai.api.data.image.edit.ImageEditData;
import io.github.jetkai.openai.api.data.image.variation.ImageVariationData;
import io.github.jetkai.openai.api.data.transcription.TranscriptionData;
import io.github.jetkai.openai.api.data.translation.TranslationData;
import io.github.jetkai.openai.util.ConvertImageFormat;

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

/**
 * RequestBuilder
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.0.0
 * @created 02/03/2023
 * @last-update 03/03/2023
 */
public abstract class RequestBuilder {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
            "(KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36";

    public abstract HttpRequest request(Object data);

    public HttpRequest buildGetRequest(URI uri, String apiKey, String organization) {
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

    public HttpRequest buildPostRequest(URI uri, Object data, String apiKey, String organization) {
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

    public HttpRequest buildMultiDataPost(URI uri, Object data, String apiKey, String organization) {
        Map<Object, Object> map = new LinkedHashMap<>();
        populateMap(data, map);

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

    private <T> void populateMap(T data, Map<Object, Object> map) {

        /*
         * Handle Transcription
         */
        if(data instanceof TranscriptionData) {
            Path audioFile = ((TranscriptionData) data).getFile();
            if (audioFile != null) {
                map.put("file", audioFile); //Required
            }
            String model = ((TranscriptionData) data).getModel();
            if (model != null) {
                map.put("model", model); //Required
            }
            String prompt = ((TranscriptionData) data).getPrompt();
            if (prompt != null && !prompt.isEmpty()) {
                map.put("prompt", prompt); //Optional
            }
            double temperature = ((TranscriptionData) data).getTemperature();
            map.put("temperature", temperature); //Optional
            String language = ((TranscriptionData) data).getLanguage();
            if (language != null && !language.isEmpty()) {
                map.put("language", language); //Optional
            }
            String responseFormat = ((TranscriptionData) data).getResponseFormat();
            if (responseFormat != null && !responseFormat.isEmpty()) {
                map.put("response_format", responseFormat); //Optional
            }

            /*
             * Handle Translation
             */
        } else if(data instanceof TranslationData) {
            Path audioFile = ((TranslationData) data).getFile();
            if(audioFile != null) {
                map.put("file", audioFile); //Required
            }
            String model = ((TranslationData) data).getModel();
            if(model != null) {
                map.put("model", model); //Required
            }
            String prompt = ((TranslationData) data).getPrompt();
            if(prompt != null && !prompt.isEmpty()) {
                map.put("prompt", prompt); //Optional
            }
            double temperature = ((TranslationData) data).getTemperature();
            map.put("temperature", temperature); //Optional
            String responseFormat = ((TranslationData) data).getResponseFormat();
            if(responseFormat != null && !responseFormat.isEmpty()) {
                map.put("response_format", responseFormat); //Optional
            }
            String language = ((TranslationData) data).getLanguage();
            if(language != null && !language.isEmpty()) {
                map.put("language", language);
            }

            /*
             * Handle ImageVariation
             */
        } else if(data instanceof ImageVariationData) {
            Path image = ((ImageVariationData) data).getImage();
            if(image != null) {
                Path newImagePath = ConvertImageFormat.convertPngToRGBA(image);
                map.put("image", newImagePath);  //Required
            }
            int n = ((ImageVariationData) data).getN();
            if(n > 0 && n < 11) {
                map.put("n", n); //Optional
            }
            String size = ((ImageVariationData) data).getSize();
            if(size != null && !size.isEmpty()) {
                map.put("size", size); //Optional
            }
            String responseFormat = ((ImageVariationData) data).getResponseFormat();
            if(responseFormat != null && !responseFormat.isEmpty()) {
                map.put("response_format", responseFormat);
            }
            String user = ((ImageVariationData) data).getUser();
            if(user != null && !user.isEmpty()) {
                map.put("user", user);
            }

            /*
             * Handle ImageEditData
             */

        } else if(data instanceof ImageEditData) {
            Path image = ((ImageEditData) data).getImage();
            if(image != null) {
                Path newImagePath = ConvertImageFormat.convertPngToRGBA(image);
                map.put("image", newImagePath); //Required
            }
            String prompt = ((ImageEditData) data).getPrompt();
            if(prompt != null && !prompt.isEmpty()) {
                map.put("prompt", prompt); //Required
            }
            Path mask = ((ImageEditData) data).getMask();
            if(mask != null) {
                Path newMaskPath = ConvertImageFormat.convertPngToRGBA(mask);
                map.put("mask", newMaskPath); //Optional
            }
            int n = ((ImageEditData) data).getN();
            if(n > 0 && n < 11) {
                map.put("n", n);   //Optional
            }
            String size = ((ImageEditData) data).getSize();
            if(size != null && !size.isEmpty()) {
                map.put("size", size); //Optional
            }
            String responseFormat = ((ImageEditData) data).getResponseFormat();
            if(responseFormat != null && !responseFormat.isEmpty()) {
                map.put("response_format", responseFormat); //Optional
            }
            String user = ((ImageEditData) data).getUser();
            if(user != null && !user.isEmpty()) {
                map.put("user", user); //Optional
            }
        }
    }

}
