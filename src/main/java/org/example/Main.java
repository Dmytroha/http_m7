package org.example;

import org.apache.log4j.BasicConfigurator;
import org.example.http.HttpImageStatusCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException, InterruptedException {

        BasicConfigurator.configure();
        logger.info("Application started.");
        HttpImageStatusCli httpImageStatusCli = new HttpImageStatusCli();
        try {
            httpImageStatusCli.askStatus();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}