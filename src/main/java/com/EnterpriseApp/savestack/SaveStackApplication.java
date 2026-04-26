package com.EnterpriseApp.savestack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the SaveStack Spring Boot application.
 */
@SpringBootApplication
public class SaveStackApplication {

    /**
     * Starts the SaveStack application.
     *
     * @param args command-line arguments passed when the application starts
     */
    public static void main(String[] args) {
        SpringApplication.run(SaveStackApplication.class, args);
    }
}