package com.mgellert.adventofcode.day13;

import com.mgellert.adventofcode.day12.GardenGroups;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClawContraption {


    private final Pattern buttonPattern = Pattern.compile("^Button [A|B]: X\\+(\\d+), Y\\+(\\d+)$");
    private final Pattern pricePattern = Pattern.compile("^Prize: X=(\\d+), Y=(\\d+)$");


    public long fewestTokensToSpend(List<List<String>> blocks) {

        return blocks.stream()
                .map(this::parseBlock)
                .map(this::minimumTokens)
                .filter(Objects::nonNull)
                .mapToLong(n -> n)
                .sum();
    }

    private Long minimumTokens(Machine machine) {
        Set<Long> wins = new HashSet<>();

        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100; b++) {
                Point p = new Point(machine.a.x * a + machine.b.x * b, machine.a.y * a + machine.b.y * b);
                if (machine.price.equals(p)) {
                    wins.add(a * 3L + b);
                }
            }
        }

        if (wins.isEmpty()) return null;
        return wins.stream().mapToLong(n -> n).min().orElseThrow();
    }

    private Machine parseBlock(List<String> block) {
        Matcher matcherA = buttonPattern.matcher(block.get(0));
        Point a = null;
        if (matcherA.find()) {
            a = new Point(Integer.parseInt(matcherA.group(1)), Integer.parseInt(matcherA.group(2)));
        }

        Matcher matcherB = buttonPattern.matcher(block.get(1));
        Point b = null;
        if (matcherB.find()) {
            b = new Point(Integer.parseInt(matcherB.group(1)), Integer.parseInt(matcherB.group(2)));
        }

        Matcher matcherPrice = pricePattern.matcher(block.get(2));
        Point price = null;
        if (matcherPrice.find()) {
            price = new Point(Integer.parseInt(matcherPrice.group(1)), Integer.parseInt(matcherPrice.group(2)));
        }

        return new Machine(a, b, price);
    }

    private record Machine(Point a, Point b, Point price) {
    }

    private record Point(long x, long y) {
    }
}
