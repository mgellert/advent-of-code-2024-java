package com.mgellert.adventofcode.day14;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestroomRedoubt {

    private final Pattern pattern = Pattern.compile("^p=(\\d+),(\\d+) v=([-\\d]+),([-\\d]+)$");


    public long safetyFactor(List<String> lines, int seconds, int width, int height) {
        List<Robot> robots = parse(lines);
        for (int i = 0; i < seconds; i++) {
            robots = step(robots, width, height);
        }
        return calculateSafety(robots, width, height);
    }

    public long findChristmasTree(List<String> lines, int width, int height) {
        List<Robot> robots = parse(lines);
        long i = 0;
        while (!isChristmasTree(robots, width, height) && i < 100_000) {
            robots = step(robots, width, height);
            i++;
        }
        return i;
    }

    private boolean isChristmasTree(List<Robot> robots, int width, int height) {
        var halfWidth = width / 2;
        var halfHeight = height / 2;

        var a = 1.0 * count(robots, 0, halfWidth, 0, halfHeight);
        var b = 1.0 * count(robots, halfWidth + 1, width, 0, halfHeight);
        var c = 1.0 * count(robots, 0, halfWidth, halfHeight + 1, height);
        var d = 1.0 * count(robots, halfWidth + 1, width, halfHeight + 1, height);

        var l = new ArrayList<>(List.of(a, b, c, d));
        Collections.sort(l);
        return l.getLast() / l.get(2) > 2.4;
    }

    private long calculateSafety(List<Robot> robots, int width, int height) {
        var halfWidth = width / 2;
        var halfHeight = height / 2;

        return count(robots, 0, halfWidth, 0, halfHeight) *
                count(robots, halfWidth + 1, width, 0, halfHeight) *
                count(robots, 0, halfWidth, halfHeight + 1, height) *
                count(robots, halfWidth + 1, width, halfHeight + 1, height);
    }

    private long count(List<Robot> robots, int w1, int w2, int h1, int h2) {
        return robots.stream()
                .filter(r ->
                        r.position.x >= w1 && r.position.x < w2 && r.position.y >= h1 && r.position.y < h2
                ).count();
    }

    private List<Robot> step(List<Robot> robots, int width, int height) {
        List<Robot> next = new ArrayList<>();
        for (Robot robot : robots) {
            var x = robot.position.x + robot.delta.x;
            if (x < 0) x += width;
            if (x >= width) x -= width;

            var y = robot.position.y + robot.delta.y;
            if (y < 0) y += height;
            if (y >= height) y -= height;

            next.add(new Robot(new Point(x, y), robot.delta));
        }
        return next;
    }

    private List<Robot> parse(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        return new Robot(
                                new Point(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2))),
                                new Point(Long.parseLong(matcher.group(3)), Long.parseLong(matcher.group(4)))
                        );
                    } else {
                        throw new IllegalStateException();
                    }
                }).toList();
    }

    private record Robot(Point position, Point delta) {
    }

    private record Point(long x, long y) {
        Point plus(Point other) {
            return new Point(this.x + other.x, this.y + other.y);
        }
    }
}
