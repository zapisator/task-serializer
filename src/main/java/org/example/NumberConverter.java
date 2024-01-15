package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static org.example.Ascii.FIRST_PRINTABLE_INDEX;

public class NumberConverter {

    public static final int BASE = Ascii.ASCII_PRINTABLE - Escape.values().length;
    private static final char BASE_ZERO = (char) (FIRST_PRINTABLE_INDEX + Escape.values().length);

    private NumberConverter() {
    }

    public static int toDecimal(String num) {
        int val = 0;
        int power = 1;
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = charToDecimal(num.charAt(i));
            if (digit < 0 || digit >= BASE) {
                return -1;
            }
            val += digit * power;
            power = power * BASE;
        }
        return val;
    }

    public static int charToDecimal(char c) {
        return c - BASE_ZERO;
    }

    public static String[] toStringOfBase95(int[] numbers) {
        return Arrays.stream(numbers)
                .mapToObj(NumberConverter::toStringOfBase95)
                .toArray(String[]::new);
    }

    public static String toStringOfBase95(int number) throws IllegalArgumentException {
        final char[] asciiPrintableChars = IntStream
                .rangeClosed(FIRST_PRINTABLE_INDEX + Escape.values().length, Ascii.LAST_PRINTABLE_INDEX)
                .mapToObj(i -> (char) i)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString
                ))
                .toCharArray();
        final StringBuilder result = new StringBuilder();
        int remainder;
        while (number > 0) {
            remainder = number % BASE;
            result.append(asciiPrintableChars[remainder]);
            number /= BASE;
        }
        return new String(
                new StringBuffer(result)
                        .reverse()
                        .toString()
                        .getBytes(StandardCharsets.ISO_8859_1)
        );
    }

    public static boolean isNotOneSymbol(int number) {
        return number / BASE > 0;
    }

    public static String assureTwoSymbols(String number) {
        return number.length() == 2 ? number : BASE_ZERO + number;
    }
}