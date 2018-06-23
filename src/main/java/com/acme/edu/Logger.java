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

    private static void output(StringBuilder decoratedMessage) {
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
        decoratedArrayMessage.append("{");
        arrayAssemble(message, decoratedArrayMessage);
        decoratedArrayMessage.append("}");
        output(decoratedArrayMessage);
    }

    private static void arrayAssemble(int[] message, StringBuilder decoratedArrayMessage) {
        for (int i = 0; i < (message.length - 1); i++) {
            decoratedArrayMessage.append(message[i]).append(", ");
        }
        decoratedArrayMessage.append(message[message.length - 1]);
    }

    public static void log(int[][] message) {
        StringBuilder decoratedMatrixMessage = new StringBuilder("primitives matrix: {" + System.lineSeparator());
        matrixAssemble(message, decoratedMatrixMessage);
        decoratedMatrixMessage.append("}");
        output(decoratedMatrixMessage);
    }

    private static void matrixAssemble(int[][] message, StringBuilder decoratedMatrixMessage) {
        StringBuilder arrayLine = new StringBuilder();

        for (int[] innerArray : message) {
            decoratedMatrixMessage.append("{");
            arrayAssemble(innerArray, arrayLine);
            decoratedMatrixMessage.append(arrayLine).append("}").append(System.lineSeparator());
            arrayLine.setLength(0);
        }
    }

    public static void log(int[][][][] message) {
        StringBuilder decoratedMultiMatrixMessage = new StringBuilder("primitives multimatrix: {" + System.lineSeparator());
        StringBuilder arrayLine = new StringBuilder();

        for(int i = 0; i < message.length; i++){
            decoratedMultiMatrixMessage.append("{").append(System.lineSeparator());
            for(int j = 0; j < message[i].length; j++){
                decoratedMultiMatrixMessage.append("{").append(System.lineSeparator());
                for (int k = 0; k < message[i][j].length; k++){
                    decoratedMultiMatrixMessage.append("{").append(System.lineSeparator());
                    arrayAssemble(message[i][j][k],arrayLine);
                    decoratedMultiMatrixMessage.append(arrayLine).append(System.lineSeparator()).append("}").append(System.lineSeparator());
                }
                decoratedMultiMatrixMessage.append("}").append(System.lineSeparator());
            }
            decoratedMultiMatrixMessage.append("}").append(System.lineSeparator());
        }
        decoratedMultiMatrixMessage.append("}");
        output(decoratedMultiMatrixMessage);
    }

    public static void log(String... message) {
        StringBuilder decoratedString = new StringBuilder();
        for (String row:
             message) {
            decoratedString.append(row).append(System.lineSeparator());
        }
        output(decoratedString);
    }

    public static void log(int message1, int... message2) {
        StringBuilder decoratedInt = new StringBuilder();
        decoratedInt.append(message1);
        for (int num :
                message2) {
            decoratedInt.append(num).append(System.lineSeparator());
        }
        output(decoratedInt);
    }
}


