package org.example.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

import static org.example.AppConstants.*;

public class HttpImageStatusCli {
    public static final Logger LOGGER = LoggerFactory.getLogger(HttpImageStatusCli.class);
    public void askStatus() throws IOException, InterruptedException {
        HttpStatusImageDownloader imageDownloader = new HttpStatusImageDownloader();
        Scanner scanner = new Scanner(System.in);
        LOGGER.info(INPUT_PROMPT, EXIT_WORD);
        do {
            try {
                if (!scanner.hasNext(EXIT_WORD)) {
                    int code = scanner.nextInt();
                    downloadImage(imageDownloader, scanner, code);
                }
            } catch (InputMismatchException e) {
                LOGGER.info(INPUT_VALID_NO_PROMPT, EXIT_WORD);
                scanner.nextLine();
            }
        } while (!scanner.hasNext(EXIT_WORD));
    }

    private void downloadImage(HttpStatusImageDownloader imageDownloader, Scanner scanner, int code) throws InterruptedException, IOException {
        try {
            imageDownloader.downloadStatusImage(code);
            LOGGER.info(INPUT_PROMPT, EXIT_WORD);
        } catch (InputMismatchException e) {
            LOGGER.info("There is not image for HTTP status {}", code);
            LOGGER.info(INPUT_VALID_NO_PROMPT, EXIT_WORD);
            scanner.nextLine();
        }
    }


}
