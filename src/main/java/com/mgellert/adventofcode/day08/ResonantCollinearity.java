package com.mgellert.adventofcode.day08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class ResonantCollinearity {

    public long countAntinodesOnMap(List<String> lines) {
        Map<Character, Set<Point>> antennas = parseInput(lines);

        Set<Point> antinodes = new HashSet<>();
        for (Character frequency : antennas.keySet()) {
            for (Point a : antennas.get(frequency)) {
                for (Point b : antennas.get(frequency)) {
                    if (a != b) {
                        antinodes.add(calculateAntinode(a, b));
                        antinodes.add(calculateAntinode(b, a));
                    }
                }
            }
        }

        return antinodes.stream()
                .filter(isInBounds(lines.size()))
                .count();
    }

    private static Predicate<Point> isInBounds(int size) {
        return a -> a.x >= 0 && a.x < size && a.y >= 0 && a.y < size;
    }

    private static Map<Character, Set<Point>> parseInput(List<String> lines) {
        Map<Character, Set<Point>> antennas = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var c = lines.get(i).charAt(j);
                if (c != '.') {
                    if (!antennas.containsKey(c)) {
                        antennas.put(c, new HashSet<>());
                    }
                    antennas.get(c).add(new Point(j, i));
                }
            }
        }
        return antennas;
    }

    private Point calculateAntinode(Point a, Point b) {
        var dx = b.x - a.x;
        var dy = b.y - a.y;
        return new Point(a.x - dx, a.y - dy);
    }

    private record Point(int x, int y) {
    }
}
