package com.mgellert.adventofcode.day05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintQueue {

    public long sumOfMiddleOfCorrectUpdates(List<String> lines) {
        final Map<Long, Rule> rules = parseRules(lines);
        final List<List<Long>> updates = parseUpdates(lines);

        return updates.stream()
                .filter(u -> isCorrect(u, rules))
                .mapToLong(u -> u.get((u.size() / 2)))
                .sum();
    }

    public long sumOfMiddleOfCorrectedUpdates(List<String> lines) {
        final Map<Long, Rule> rules = parseRules(lines);
        final List<List<Long>> updates = parseUpdates(lines);

        return updates.stream()
                .filter(u -> !isCorrect(u, rules))
                .map(u -> makeCorrect(u, rules))
                .mapToLong(u -> u.get((u.size() / 2)))
                .sum();
    }

    private List<Long> makeCorrect(List<Long> update, Map<Long, Rule> rules) {
        return update.stream().sorted((num, other) -> {
            if (rules.get(num).before.contains(other)) {
                return -1;
            } else {
                return 1;
            }
        }).toList();
    }

    private boolean isCorrect(List<Long> update, Map<Long, Rule> rules) {
        for (int i = 0; i < update.size(); i++) {
            var num = update.get(i);
            var rule = rules.get(num);

            var after = update.subList(0, i);
            var before = update.subList(i + 1, update.size());

            if (!rule.after.containsAll(after) || !rule.before.containsAll(before)) {
                return false;
            }
        }

        return true;
    }

    private static class Rule {
        long page;
        Set<Long> before = new HashSet<>();
        Set<Long> after = new HashSet<>();

        public Rule(long page) {
            this.page = page;
        }
    }

    private Map<Long, Rule> parseRules(List<String> lines) {
        var divider = lines.indexOf("");
        var rawRules = lines.subList(0, divider);

        Map<Long, Rule> rules = new HashMap<>();

        rawRules.forEach(rawRule -> {
            var split = Arrays.stream(rawRule.split("\\|")).map(Long::parseLong).toList();
            var left = split.get(0);
            var right = split.get(1);

            if (!rules.containsKey(left)) {
                rules.put(left, new Rule(left));
            }
            if (!rules.containsKey(right)) {
                rules.put(right, new Rule(right));
            }

            rules.get(left).before.add(right);
            rules.get(right).after.add(left);
        });

        return rules;
    }

    private List<List<Long>> parseUpdates(List<String> lines) {
        var divider = lines.indexOf("");
        var rawUpdates = lines.subList(divider + 1, lines.size());

        return rawUpdates.stream()
                .map(line -> Arrays.stream(line.split(",")).map(Long::parseLong).toList())
                .toList();
    }
}
