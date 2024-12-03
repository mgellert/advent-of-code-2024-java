package com.mgellert.adventofcode.day01;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HistorianHysteria {

    private final Pattern pattern = Pattern.compile("(\\d+)\\W+(\\d+)");

    public Long findTotalDistance(List<String> lines) {
        var lists = parseInput(lines);

        Collections.sort(lists.getLeft());
        Collections.sort(lists.getRight());

        long distance = 0L;

        for (int i = 0; i < lists.getLeft().size(); i++) {
            distance += Math.abs(lists.getLeft().get(i) - lists.getRight().get(i));
        }

        return distance;
    }

    private Pair<List<Long>, List<Long>> parseInput(List<String> lines) {
        var list1 = new ArrayList<Long>();
        var list2 = new ArrayList<Long>();

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                list1.add(Long.parseLong(matcher.group(1)));
                list2.add(Long.parseLong(matcher.group(2)));
            }
        }

        return Pair.of(list1, list2);
    }

    public Long calculateSimilarityScore(List<String> lines) {
        var lists = parseInput(lines);

        Map<Long, Long> counted = lists.getRight().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long similarityScore = 0L;

        for (int i = 0; i < lists.getLeft().size(); i++) {
            var left = lists.getLeft().get(i);
            similarityScore += left * counted.getOrDefault(left, 0L);
        }

        return similarityScore;
    }

}
