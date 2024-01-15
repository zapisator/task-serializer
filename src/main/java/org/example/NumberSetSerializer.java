package org.example;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.example.Escape.CONSEQUENCES;
import static org.example.Escape.FREQUENCIES;
import static org.example.Escape.SOLITARIES_ONE_SYMBOL;
import static org.example.Escape.SOLITARIES_TWO_SYMBOL;
import static org.example.NumberConverter.assureTwoSymbols;
import static org.example.NumberConverter.isNotOneSymbol;
import static org.example.NumberConverter.toDecimal;
import static org.example.NumberConverter.toStringOfBase95;

@RequiredArgsConstructor
public class NumberSetSerializer {

    private final Analyzer analyzer;

    public String serialize() {
        return frequenciesSerialized() + consequencesSerialized() + solitariesSerialized();
    }

    public int[] deserialize(String serialized) {
        return serializedParts(serialized)
                .entrySet().stream()
                .map(entry -> deserializer(entry.getKey()).apply(entry.getValue()))
                .flatMap(Collection::stream)
                .mapToInt(integer -> integer)
                .sorted()
                .toArray();
    }

    public String frequenciesSerialized() {
        final String frequentNumbers = analyzer.frequentNumbers().stream()
                .map(pair ->
                        assureTwoSymbols(toStringOfBase95(pair.getLeft()))
                                + assureTwoSymbols(toStringOfBase95(pair.getRight())))
                .collect(Collectors.joining());
        return FREQUENCIES.getCharacter() + frequentNumbers;
    }

    public String consequencesSerialized() {
        final String consequences = analyzer.consequences().stream()
                .map(pair ->
                        assureTwoSymbols(toStringOfBase95(pair.getLeft()))
                                + assureTwoSymbols(toStringOfBase95(pair.getRight())))
                .collect(Collectors.joining());
        return CONSEQUENCES.getCharacter() + consequences;
    }

    public String solitariesSerialized() {
        return analyzer.solitaries().stream()
                .collect(Collectors.groupingBy(integer -> isNotOneSymbol(integer) ? 1 : 0))
                .entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(entry ->
                        (entry.getKey() == 0
                                ? String.valueOf(SOLITARIES_ONE_SYMBOL.getCharacter())
                                : SOLITARIES_TWO_SYMBOL.getCharacter())
                                + entry.getValue().stream()
                                .map(NumberConverter::toStringOfBase95)
                                .collect(Collectors.joining("")))
                .collect(Collectors.joining());
    }

    private Function<String, List<Integer>> deserializer(Escape escape) {
        return switch (escape) {
            case FREQUENCIES -> this::frequenciesDeserialized;
            case CONSEQUENCES -> this::consequencesDeserialized;
            case SOLITARIES_ONE_SYMBOL -> this::solitariesOneSymbolDeserialized;
            case SOLITARIES_TWO_SYMBOL -> this::solitariesTwoSymbolsDeserialized;
        };
    }

    public List<Integer> frequenciesDeserialized(String frequencies) {
        return frequencies
                .substring(1)
                .chars()
                .mapToObj(i -> (char) i)
                .reduce(
                        new LinkedList<String>(),
                        (list, character) -> {
                            if (list.isEmpty() || list.peekLast().length() == 4) {
                                list.add("" + character);
                            } else {
                                list.set(list.size() - 1, list.peekLast() + character);
                            }
                            return list;
                        },
                        (list1, list2) -> list1
                ).stream()
                .flatMap(str -> Stream.generate(() -> toDecimal(str.substring(0, 2)))
                        .limit(toDecimal(str.substring(2, 4)))
                )
                .toList();
    }

    public List<Integer> consequencesDeserialized(String consequences) {
        return consequences
                .substring(1)
                .chars()
                .mapToObj(i -> (char) i)
                .reduce(
                        new LinkedList<String>(),
                        (list, character) -> {
                            if (list.isEmpty() || list.peekLast().length() == 4) {
                                list.add("" + character);
                            } else {
                                list.set(list.size() - 1, list.peekLast() + character);
                            }
                            return list;
                        },
                        (list1, list2) -> list1
                ).stream()
                .flatMap(str -> IntStream.rangeClosed(
                                toDecimal(str.substring(0, 2)),
                                toDecimal(str.substring(2, 4)))
                        .boxed()
                )
                .toList();
    }

    public List<Integer> solitariesOneSymbolDeserialized(String solitaries) {
        return solitaries.substring(1)
                .chars()
                .mapToObj(i -> (char) i)
                .map(String::valueOf)
                .map(NumberConverter::toDecimal)
                .toList();
    }

    public List<Integer> solitariesTwoSymbolsDeserialized(String solitaries) {
        return solitaries.substring(1)
                .chars()
                .mapToObj(i -> (char) i)
                .reduce(
                        new LinkedList<String>(),
                        (list, character) -> {
                            if (list.isEmpty() || list.peekLast().length() == 2) {
                                list.add("" + character);
                            } else {
                                list.set(list.size() - 1, list.peekLast() + character);
                            }
                            return list;
                        },
                        (list1, list2) -> list1
                ).stream()
                .map(NumberConverter::toDecimal)
                .toList();
    }

    public Map<Escape, String> serializedParts(String serialized) {
        return Stream.of(
                        serialized.indexOf(FREQUENCIES.getCharacter()),
                        serialized.indexOf(CONSEQUENCES.getCharacter()),
                        serialized.indexOf(SOLITARIES_ONE_SYMBOL.getCharacter()),
                        serialized.indexOf(SOLITARIES_TWO_SYMBOL.getCharacter()),
                        serialized.length()
                ).filter(integer -> integer >= 0)
                .reduce(
                        new LinkedList<Pair<Integer, Integer>>(),
                        (list, index) -> {
                            if (list.isEmpty()) {
                                list.add(Pair.of(index, index));
                            } else if (list.size() == 1 && list.peekLast().getLeft() == list.peekLast().getRight()) {
                                list.add(Pair.of(list.poll().getLeft(), index));
                            } else {
                                list.add(Pair.of(list.peekLast().getRight(), index));
                            }
                            return list;
                        },
                        (list1, list2) -> list1
                ).stream()
                .map(pair -> serialized.substring(pair.getLeft(), pair.getRight()))
                .collect(Collectors.toMap(str -> Escape.fromCharacter(str.charAt(0)), str -> str));
    }

}
