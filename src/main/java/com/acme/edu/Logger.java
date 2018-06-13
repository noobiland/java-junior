package com.acme.edu;

public class Logger {
    private static int integerSum = 0;

    public static void logIntBuffer(){
        output("primitive: " + integerSum);
        integerSum = 0;
    }

    public static void log(int message) {
        integerSum += message;
    }

    public static void log(byte message) {
        logIntBuffer();
        String decoratedMessage = "primitive: " + message;
        output(decoratedMessage);
    }

    private static void output(String decoratedMessage) {
        System.out.println(decoratedMessage);
    }

    public static void log (char message) {
        logIntBuffer();
        output("char: " + message);
    }

    public static void log (String message) {
        logIntBuffer();
        output("string: " + message);
    }

    public static void log (boolean message) {
        logIntBuffer();
        output("primitive: " + message);
    }

    public static void log (Object message) {
        logIntBuffer();
        output("reference: " + "@");
    }



}
