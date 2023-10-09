package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Logger log;

    public static void main(String[] args) {
        log = LogManager.getLogger(Main.class);
        log.info("hello world");
    }

}