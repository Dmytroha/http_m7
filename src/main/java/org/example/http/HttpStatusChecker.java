package org.example.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.AppConstants.URL_HTTP_CAT;

public class HttpStatusChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpStatusChecker.class);
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static String getStatusImage(int code) throws InterruptedException, IOException {
        URI linkToImage = URI.create(String.format(URL_HTTP_CAT+"/%s.jpg", code));
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(linkToImage)
                    .GET()
                    .build();
            LOGGER.info("Request status code: -> {}", getStatusCode(httpRequest));
            if (getStatusCode(httpRequest) == 404) {
                throw new InputMismatchException("Unknown status code provided! Need valid status code!");
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return String.valueOf(linkToImage);
    }

    private static int getStatusCode(HttpRequest httpRequest) throws InterruptedException, IOException {
        HttpResponse<String> httpResponse = CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponse.statusCode();
    }

}
