package org.example;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnalyzerTest {

    private final ArrayProvider arrayProvider = new ArrayProvider();

    void printVariables(Analyzer analyzer, int[] numbers) {
        System.out.println("     numbers: " + Arrays
                .stream(numbers)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(",")));
        System.out.println("  frequences: " + analyzer.frequencies());
        System.out.println("consequences: " + analyzer.consequences());
        System.out.println("  solitaries: " + analyzer.solitaries());
        System.out.println("   frequents: " + analyzer.frequentNumbers());
        System.out.println("=====\n");
    }

    @Test
    void onlyConsequentNumbers() {
        final int[] numbers = {1, 2, 3, 4, 11, 12, 13, 14};
        final List<Pair<Integer, Integer>> expected = List.of(
                Pair.of(1, 4),
                Pair.of(11, 14)
        );
        final Analyzer analyzer = Analyzer.create(numbers);

        printVariables(analyzer, numbers);

        assertAll(
                () -> assertEquals(expected, analyzer.consequences()),
                () -> assertTrue(analyzer.frequentNumbers().isEmpty()),
                () -> assertTrue(analyzer.solitaries().isEmpty())
        );
    }

    @Test
    void onlyFrequentNumbers() {
        final int[] numbers = {1, 1, 1, 4, 4, 4, 6, 6};
        final List<Pair<Integer, Integer>> expected = List.of(
                Pair.of(1, 3),
                Pair.of(4, 3),
                Pair.of(6, 2)
        );
        final Analyzer analyzer = Analyzer.create(numbers);

        printVariables(analyzer, numbers);

        assertAll(
                () -> assertTrue(analyzer.consequences().isEmpty()),
                () -> assertEquals(expected, analyzer.frequentNumbers()),
                () -> assertTrue(analyzer.solitaries().isEmpty())
        );
    }

    @Test
    void onlySolitaryNumbers() {
        final int[] numbers = {1, 3, 5, 7, 9};
        final List<Integer> expected = List.of(1, 3, 5, 7, 9);
        final Analyzer analyzer = Analyzer.create(numbers);

        printVariables(analyzer, numbers);

        assertAll(
                () -> assertTrue(analyzer.consequences().isEmpty()),
                () -> assertTrue(analyzer.frequentNumbers().isEmpty()),
                () -> assertEquals(expected, analyzer.solitaries())
        );
    }
}