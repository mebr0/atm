package com.mebr0.util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Logger {

    private static Logger logger = null;

    private static final String PREFIX = "logs/";
    private static final String FILE = "log.txt";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {

    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }

        return logger;
    }

    /**
     * Prints info to stdout
     */
    public void info(String message) {
        message = now() + " [" + Thread.currentThread().getId() + "] [INFO] " + message;

        writeToFile(message);
        System.out.println(message);
    }

    /**
     * Prints info to stderr
     */
    public void error(String message) {
        message = now() + " [" + Thread.currentThread().getId() + "] [ERROR] " + message;

        writeToFile(message);
        System.err.println(message);
    }

    /**
     * Writes message to file
     */
    private void writeToFile(String message) {
        try (FileWriter writer = new FileWriter(PREFIX + FILE, true)) {
            writer.write(message + '\n');
        }
        catch (IOException e) {
            System.err.println("Cannot log to file");
        }
    }

    /**
     * Get now with {@link #FORMAT}
     */
    private String now() {
        return LocalDateTime.now().format(FORMAT);
    }
}
