package com.mgellert.adventofcode.day08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResonantCollinearity {

    public long countAntinodesOnMapWithHarmonics(List<String> lines) {
        final var size = lines.size();
        Map<Character, Set<Point>> antennas = parseInput(lines);

        Set<Point> antinodes = new HashSet<>();
        for (Character frequency : antennas.keySet()) {
            for (Point a : antennas.get(frequency)) {
                for (Point b : antennas.get(frequency)) {
                    if (a != b) {
                        antinodes.addAll(calculateHarmonics(a, b, size));
                        antinodes.addAll(calculateHarmonics(b, a, size));
                    }
                }
            }
        }

        return antinodes.stream()
                .filter(p -> isInBounds(p, size))
                .count();
    }


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
                .filter(p -> isInBounds(p, lines.size()))
                .count();
    }

    private static boolean isInBounds(Point p, int size) {
        return p.x >= 0 && p.x < size && p.y >= 0 && p.y < size;
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

    private Set<Point> calculateHarmonics(Point b, Point a, int size) {
        var dx = b.x - a.x;
        var dy = b.y - a.y;

        Set<Point> antinodes = new HashSet<>();

        Point p;
        int i = 0;
        do {
            p = new Point(a.x - (i * dx), a.y - (i * dy));
            antinodes.add(p);
            i++;
        } while (isInBounds(p, size));
        return antinodes;
    }

    private record Point(int x, int y) {
    }
}
