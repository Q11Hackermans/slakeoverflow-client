package com.github.q11hackermans.slakeoverflow_client.utility;

public class Logger {
    public static void writeLine(String message, String color) {
        System.out.println(color + message + Colors.CONSOLE_RESET);
    }


    /*
    Simple colored logs
     */
    public static void error(String message) {
        writeLine("ERROR  | " + message, Colors.CONSOLE_ERROR);
    }

    public static void info(String message) {
        writeLine("INFO   | " + message, Colors.CONSOLE_INFO);
    }

    public static void debug(String message) {
        writeLine("DEBUG  | " + message, Colors.CONSOLE_DEBUG);
    }
}
