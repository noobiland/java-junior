package com.acme.edu;

public class Logger {
    private static long integerSum = 0;
    private static int stringCounter = 1;
    private static String prevState = new String("");
    public static void flushIntBuffer(){
        output("primitive: " + integerSum);
        integerSum = 0;
    }

    public static void log(int message) {
        if ((integerSum + message) >= Integer.MAX_VALUE) {
            flushIntBuffer();
            output("primitive: " + Integer.MAX_VALUE);
            return;
        }
        //put here flushstring
        integerSum += message;
    }

    public static void log(byte message) {
        flushIntBuffer();
        String decoratedMessage = "primitive: " + message;
        output(decoratedMessage);
    }

    private static void output(String decoratedMessage) {
        System.out.println(decoratedMessage);
    }

    public static void log (char message) {
        flushIntBuffer();
        output("char: " + message);
    }

    public static void log (String message) {
        flushIntBuffer();
        if (!message.equals(prevState)){
            flushStringBuffer(message);
        }else {
            stringCounter++;
            System.out.println("stringCounter:" + stringCounter);
        }

    }

    public static void flushStringBuffer(String message) {
        if(stringCounter==1) {
            output("string: " + message);
            prevState = message;
        }else {
            output("string: " + message + "(x"+stringCounter+")");
            prevState = message;
            stringCounter = 1;
        }
    }

    public static void log (boolean message) {
        flushIntBuffer();
        output("primitive: " + message);
    }

    public static void log (Object message) {
        flushIntBuffer();
        output("reference: " + "@");
    }


    public static void flush() {

    }
}
