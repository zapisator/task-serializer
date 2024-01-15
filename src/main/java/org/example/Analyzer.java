package org.example;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Analyzer {
    private final int[] numbers;
    private Map<Integer, Integer> frequencies;
    private Map<Integer, Integer> consequences;
    private Map<Integer, Integer> frequenciesMinusConsequences;

    public static Analyzer create(int[] numbers) {
        return new Analyzer(numbers)
                .analyze();
    }

    private Analyzer analyze() {
        frequencies = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.reducing(
                                        0,
                                        number -> 1,
                                        Integer::sum
                                )
                        )
                );
        consequences = frequencies.keySet().stream()
                .sorted()
                .reduce(
                        new ArrayList<Pair<Integer, Integer>>(),
                        (list, element) -> {
                            if (list.isEmpty()) {
                                list.add(Pair.of(element, element));
                            } else {
                                final Pair<Integer, Integer> lastPair = list.get(list.size() - 1);
                                if (element == lastPair.getRight() + 1) {
                                    list.set(list.size() - 1, Pair.of(lastPair.getLeft(), element));
                                } else {
                                    list.add(Pair.of(element, element));
                                }
                            }
                            return list;
                        },
                        (list1, list2) -> list1
                ).stream()
                .filter(pair -> !pair.getLeft().equals(pair.getRight()))
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
        final Set<Integer> consequentNumbers = consequences.entrySet().stream()
                .flatMap(entry -> IntStream.rangeClosed(entry.getKey(), entry.getValue()).boxed())
                .collect(Collectors.toSet());
        frequenciesMinusConsequences = frequencies.entrySet().stream()
                .map(entry -> Pair.of(
                        entry.getKey(),
                        (consequentNumbers.contains(entry.getKey()) ? entry.getValue() - 1 : entry.getValue()))
                )
                .filter(pair -> pair.getRight() > 0)
                .collect(Collectors.toMap(Pair::getKey, Pair::getRight));
        return this;
    }

    public List<Pair<Integer, Integer>> frequencies() {
        return frequencies.entrySet().stream()
                .map(Pair::of)
                .sorted()
                .toList();
    }

    public List<Pair<Integer, Integer>> consequences() {
        return consequences.entrySet().stream()
                .map(Pair::of)
                .sorted()
                .toList();
    }

    public List<Pair<Integer, Integer>> frequentNumbers() {
        return frequenciesMinusConsequences.entrySet().stream()
                .map(Pair::of)
                .filter(pair -> pair.getRight() > 1)
                .sorted()
                .toList();
    }

    public List<Integer> solitaries() {
        return frequenciesMinusConsequences.entrySet().stream()
                .map(Pair::of)
                .filter(pair -> pair.getRight() == 1)
                .sorted()
                .map(Pair::getKey)
                .toList();
    }
}
