package com.github.q11hackermans.slakeoverflow_client.utility;

public class Logger {

    public static final String RESET = "\033[0m";
    public static final String DEBUG = "\033[0;36m";
    public static final String ERROR = "\033[0;31m";
    public static final String INFO = "\033[0m";



    public static void writeLine(String message, String color){
     System.out.println(color + message + RESET);
    }


    /*
    Simple colored logs
     */
    public static void error(String message) {
        writeLine("ERROR  | " + message, ERROR);
    }

    public static void info(String message) {
        writeLine("INFO   | " + message, INFO);
    }

    public static void debug(String message){
        writeLine("DEBUG  | " + message, DEBUG);
    }
}
