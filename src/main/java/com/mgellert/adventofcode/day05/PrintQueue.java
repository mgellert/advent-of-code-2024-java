package com.mgellert.adventofcode.day05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintQueue {

    public long sumOfMiddleOfCorrectOrders(List<String> lines) {
        var divider = lines.indexOf("");
        var rawRules = lines.subList(0, divider);
        var rawUpdates = lines.subList(divider + 1, lines.size());

        final Map<Long, Rule> rules = parseRules(rawRules);
        final List<List<Long>> updates = parseUpdates(rawUpdates);

        return updates.stream()
                .filter(u -> isCorrect(u, rules))
                .mapToLong(u -> u.get((u.size() / 2)))
                .sum();
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

    private Map<Long, Rule> parseRules(List<String> rawRules) {
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

    private List<List<Long>> parseUpdates(List<String> rawUpdates) {
        return rawUpdates.stream()
                .map(line -> Arrays.stream(line.split(",")).map(Long::parseLong).toList())
                .toList();
    }
}
