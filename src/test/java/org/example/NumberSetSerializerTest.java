package org.example;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.example.Printer.printed;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSetSerializerTest {

    private final ArrayProvider provider = new ArrayProvider();
    private NumberSetSerializer serializer;

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void shortArray5() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(5)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void shortArray10() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(10)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void shortArray50() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(50)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void shortArray100() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(100)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void shortArray500() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(500)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void shortArray1000() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(1000)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void oneSymbolArray() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(1000)
                .lowerValue(1)
                .upperValue(9)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void twoSymbolArray() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(1000)
                .lowerValue(10)
                .upperValue(99)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void threeSymbolArray() {
        final int[] numbers = provider.array(ArrayProvider.Configuration.builder()
                .count(1000)
                .lowerValue(100)
                .upperValue(300)
                .build());
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

    @RepeatedTest(value = 1000, failureThreshold = 999)
    void threeCopiesOfEach() {
        final int[] numbers = IntStream.rangeClosed(1, 300)
                .flatMap(i -> IntStream.generate(() -> i)
                        .limit(3))
                .toArray();
        final String numbersPrinted = printed(numbers);
        final int originalLength = numbersPrinted.length();

        serializer = new NumberSetSerializer(Analyzer.create(numbers));
        final String serialized = serializer.serialize();
        final int serializedLength = serialized.length();

        System.out.println("  original: " + originalLength + "\n" + numbersPrinted);
        System.out.println("serialized: " + serializedLength + "\n" + serialized);

        assertAll(
                () -> assertTrue(((double) originalLength / serializedLength) > 2.),
                () -> assertEquals(
                        Arrays.stream(numbers).boxed().toList(),
                        Arrays.stream(serializer.deserialize(serialized)).boxed().toList()
                )
        );
    }

}