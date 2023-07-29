package org.example.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.example.AppConstants.DOWNLOADS_PATH;

public class HttpStatusImageDownloader {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpStatusImageDownloader.class);
    public void downloadStatusImage(int code) throws InterruptedException, IOException {
        String imageName = String.format("%s.jpg", code);
        String imageLink = HttpStatusChecker.getStatusImage(code);
        try (InputStream input = new URL(imageLink).openStream()) {
            Files.copy(input, Paths.get(DOWNLOADS_PATH + '/'+imageName));
            LOGGER.info("Image downloaded successfully for status code - {}", code);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Image download failure, please enter valid status code");
        }
    }

}
