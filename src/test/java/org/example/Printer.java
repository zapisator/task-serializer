package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Printer {
    public static String printed(int... numbers) {
        return Arrays
                .stream(numbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));
    }
}
