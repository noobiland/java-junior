package com.acme.edu;

public class Logger {
    private static long integerSum = 0;
    private static int stringCounter = 1;
    private static String prevState = null;
    private static boolean intAccum = false;
    public static boolean stringAccum = false;

    public static void flushIntBuffer(){
        if (intAccum) {
            output("primitive: " + integerSum);
            integerSum = 0;
            intAccum = false;
        }
    }

    public static void flushStringBuffer() {
        if(stringCounter == 1){
            output("string: " + prevState);
        }
        else {
            output("string: " + prevState + " (x"+stringCounter+")");
        }
        stringCounter = 1;
        prevState = null;
    }

    public static void log(int message) {
        if(stringAccum){
            flushStringBuffer();
            stringAccum = false;
        }

        if ((integerSum + message) >= Integer.MAX_VALUE) {
            flushIntBuffer();
            output("primitive: " + Integer.MAX_VALUE);
            return;
        }
        integerSum += message;
        intAccum = true;
    }

    public static void log (String message) {
        flushIntBuffer();

        if(prevState == null){
            prevState = message;
        }
        else if (prevState.equals(message)){
            stringCounter++;
        }
        else{
            flushStringBuffer();
            prevState = message;
        }
        stringAccum = true;
    }
    public static void log(byte message) {
        flushIntBuffer();
        String decoratedMessage = "primitive: " + message;
        output(decoratedMessage);
    }

    public static void log (char message) {
        flushIntBuffer();
        output("char: " + message);
    }

    public static void log (boolean message) {
        flushIntBuffer();
        output("primitive: " + message);
    }

    public static void log (Object message) {
        flushIntBuffer();
        output("reference: " + "@");
    }

    private static void output(String decoratedMessage) {
        System.out.println(decoratedMessage);
    }
}


