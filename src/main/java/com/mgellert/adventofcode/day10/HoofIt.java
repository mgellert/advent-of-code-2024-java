package com.mgellert.adventofcode.day10;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HoofIt {

    public long sumOfTrailHeadScores(List<String> lines) {
        final var map = parseMap(lines);

        var trailHeads = map.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .collect(Collectors.toSet());

        return trailHeads.stream()
                .map(entry -> score(entry.getKey(), map, new HashSet<>()))
                .mapToLong(Set::size)
                .sum();
    }

    private Set<Point> score(Point point, Map<Point, Integer> map, HashSet<Point> reachedTops) {
        int height = map.get(point);

        if (height == 9) {
            reachedTops.add(point);
            return reachedTops;
        }

        return Arrays.stream(Direction.values())
                .filter(d -> {
                    var p = point.plus(d.delta);
                    var h = map.get(p);
                    return h - height == 1;
                }).map(d -> {
                    var p = point.plus(d.delta);
                    return score(p, map, new HashSet<>());
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public long sumOfTrailHeadRates(List<String> lines) {
        final var map = parseMap(lines);

        var trailHeads = map.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .collect(Collectors.toSet());

        return trailHeads.stream()
                .mapToLong(entry -> rate(entry.getKey(), map))
                .sum();
    }

    private long rate(Point point, Map<Point, Integer> map) {
        int height = map.get(point);

        if (height == 9) {
            return 1;
        }

        return Arrays.stream(Direction.values())
                .filter(d -> {
                    var p = point.plus(d.delta);
                    var h = map.get(p);
                    return h - height == 1;
                }).mapToLong(d -> {
                    var p = point.plus(d.delta);
                    var r = rate(p, map);

                    return r;
                })
                .sum();
    }

    private Map<Point, Integer> parseMap(List<String> lines) {
        Map<Point, Integer> map = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var c = lines.get(i).charAt(j);
                if (c == '.') {
                    map.put(new Point(j, i), -1);
                } else {
                    var height = Integer.parseInt(String.valueOf(c));
                    map.put(new Point(j, i), height);
                }

            }
        }

        for (int i = -1; i <= lines.size(); i++) {
            map.put(new Point(-1, i), -1);
            map.put(new Point(i, -1), -1);
            map.put(new Point(lines.size(), i), -1);
            map.put(new Point(i, lines.size()), -1);
        }

        return map;
    }

    private enum Direction {
        UP(new Point(0, -1)),
        RIGHT(new Point(1, 0)),
        DOWN(new Point(0, 1)),
        LEFT(new Point(-1, 0));

        final Point delta;

        Direction(Point delta) {
            this.delta = delta;
        }
    }

    private record Point(int x, int y) {
        Point plus(Point other) {
            return new Point(this.x + other.x, this.y + other.y);
        }
    }
}
