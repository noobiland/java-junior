package com.acme.edu;

public class Logger {
    private static boolean intAccum = false;
    private static boolean stringAccum = false;
    private static long integerSum = 0;
    private static int stringCounter = 1;
    private static String prevState = null;

    private static void output(String decoratedMessage) {
        System.out.println(decoratedMessage);
    }

    public static void flushIntBuffer() {
        if (intAccum) {
            output("primitive: " + integerSum);
            integerSum = 0;
            intAccum = false;
        }
    }

    public static void flushStringBuffer() {
        if (stringCounter == 1) {
            output("string: " + prevState);
        } else {
            output("string: " + prevState + " (x" + stringCounter + ")");
        }
        stringCounter = 1;
        prevState = null;
    }

    public static void log(int message) {
        if (stringAccum) {
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

    public static void log(String message) {
        flushIntBuffer();

        if (prevState == null) {
            prevState = message;
        } else if (prevState.equals(message)) {
            stringCounter++;
        } else {
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

    public static void log(char message) {
        flushIntBuffer();
        output("char: " + message);
    }

    public static void log(boolean message) {
        flushIntBuffer();
        output("primitive: " + message);
    }

    public static void log(Object message) {
        flushIntBuffer();
        output("reference: " + "@");
    }

    public static void log(int[] message) {
        StringBuilder decoratedArrayMessage = new StringBuilder("primitives array: ");
        arrayOutput(message, decoratedArrayMessage);
        System.out.println(decoratedArrayMessage);
    }

    private static void arrayOutput(int[] message, StringBuilder decoratedArrayMessage) {
        decoratedArrayMessage.append("{");

        for (int i = 0; i < (message.length - 1); i++) {
            decoratedArrayMessage.append(message[i]).append(", ");
        }

        decoratedArrayMessage.append(message[message.length - 1]).append("}");
    }

    public static void log(int[][] message) {
        StringBuilder decoratedMatrixMessage = new StringBuilder("primitives matrix: {" + System.lineSeparator());
        StringBuilder arrayLine = new StringBuilder();
        for (int[] innerArray : message) {
            arrayOutput(innerArray, arrayLine);
            decoratedMatrixMessage.append(arrayLine).append(System.lineSeparator());
            arrayLine.setLength(0);
        }

        decoratedMatrixMessage.append("}");
        System.out.println(decoratedMatrixMessage);

    }

}


