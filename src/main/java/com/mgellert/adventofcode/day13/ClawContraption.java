package com.mgellert.adventofcode.day13;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClawContraption {

    public static final long CORRECTION = 10000000000000L;

    private final Pattern buttonPattern = Pattern.compile("^Button [A|B]: X\\+(\\d+), Y\\+(\\d+)$");
    private final Pattern pricePattern = Pattern.compile("^Prize: X=(\\d+), Y=(\\d+)$");

    public long minTokensToSpend(List<List<String>> blocks) {
        return minTokensToSpend(blocks, 0L);
    }

    public long minTokensToSpend(List<List<String>> blocks, long correction) {
        return blocks.stream()
                .map((List<String> block) -> parseBlock(block, correction))
                .map(this::minimumTokensWithLinAlg)
                .filter(Objects::nonNull)
                .mapToLong(n -> n)
                .sum();
    }

    private Long minimumTokensWithLinAlg(Machine m) {
        // linear algebra with Cramer's rule
        // A = (p_x*b_y - p_y*b_x) / (a_x*b_y - a_y*b_x)
        // B = (a_x*p_y - a_y*p_x) / (a_x*b_y - a_y*b_x)

        long topA = m.price.x * m.b.y - m.price.y * m.b.x;
        long topB = m.a.x * m.price.y - m.a.y * m.price.x;
        long bottom = m.a.x * m.b.y - m.a.y * m.b.x;

        if (topA % bottom != 0 || topB % bottom != 0) return null;

        var a = topA / bottom;
        var b = topB / bottom;

        return a * 3 + b;
    }

    private Machine parseBlock(List<String> block, long correction) {
        Point a = getPoint(block, 0, buttonPattern);
        Point b = getPoint(block, 1, buttonPattern);
        Point price = getPoint(block, 2, pricePattern).plus(new Point(correction, correction));
        return new Machine(a, b, price);
    }

    private Point getPoint(List<String> block, int index, Pattern pattern) {
        Matcher matcherB = pattern.matcher(block.get(index));
        if (matcherB.find()) {
            return new Point(Integer.parseInt(matcherB.group(1)), Integer.parseInt(matcherB.group(2)));
        }
        throw new IllegalStateException();
    }

    private record Machine(Point a, Point b, Point price) {
    }

    private record Point(long x, long y) {
        Point plus(Point other) {
            return new Point(this.x + other.x, this.y + other.y);
        }
    }
}
