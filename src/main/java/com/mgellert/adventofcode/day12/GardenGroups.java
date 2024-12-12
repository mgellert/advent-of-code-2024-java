package com.mgellert.adventofcode.day12;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GardenGroups {

    public long fencingPrice(List<String> lines) {
        Set<Point> visited = new HashSet<>();
        var price = 0L;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var p = new Point(j, i);
                if (!visited.contains(p)) {
                    Set<Point> garden = floodFill(p, lines);
                    visited.addAll(garden);

                    Long perimeter = countPerimeter(garden, lines);
                    price += perimeter * garden.size();
                }
            }
        }

        return price;
    }

    public long fencingPriceWithDiscount(List<String> lines) {
        Set<Point> visited = new HashSet<>();
        var price = 0L;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var p = new Point(j, i);
                if (!visited.contains(p)) {
                    Set<Point> garden = floodFill(p, lines);
                    visited.addAll(garden);

                    Long sides = countSides(garden, lines);
                    price += sides * garden.size();
                }
            }
        }

        return price;
    }

    private Long countSides(Set<Point> garden, List<String> lines) {
        var startChar = charAt(garden.iterator().next(), lines);
        var sides = 0L;

        for (Point point : garden) {
            // hae
            // dpb
            // gcf

            var a = charAt(point.plus(Direction.UP.delta), lines) == startChar;
            var b = charAt(point.plus(Direction.RIGHT.delta), lines) == startChar;
            var c = charAt(point.plus(Direction.DOWN.delta), lines) == startChar;
            var d = charAt(point.plus(Direction.LEFT.delta), lines) == startChar;

            if ((!a && !b)) sides++;
            if ((!b && !c)) sides++;
            if ((!c && !d)) sides++;
            if ((!d && !a)) sides++;

            var e = charAt(point.plus(Direction.UP.delta).plus(Direction.RIGHT.delta), lines) == startChar;
            var f = charAt(point.plus(Direction.RIGHT.delta).plus(Direction.DOWN.delta), lines) == startChar;
            var g = charAt(point.plus(Direction.DOWN.delta).plus(Direction.LEFT.delta), lines) == startChar;
            var h = charAt(point.plus(Direction.LEFT.delta).plus(Direction.UP.delta), lines) == startChar;

            if (a && b && !e) sides++;
            if (b && c && !f) sides++;
            if (c && d && !g) sides++;
            if (d && a && !h) sides++;
        }

        return sides;
    }

    private Long countPerimeter(Set<Point> garden, List<String> lines) {
        var startChar = charAt(garden.iterator().next(), lines);
        var perimeter = 0L;

        for (Point point : garden) {
            for (Direction d : Direction.values()) {
                var p = point.plus(d.delta);
                var c = charAt(p, lines);
                if (c != startChar) {
                    perimeter++;
                }
            }
        }

        return perimeter;
    }

    private Set<Point> floodFill(Point startPoint, List<String> lines) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(startPoint);
        char startChar = charAt(startPoint, lines);

        Set<Point> inside = new HashSet<>();
        Set<Point> checked = new HashSet<>();

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            inside.add(point);

            for (Direction d : Direction.values()) {
                var p = point.plus(d.delta);

                var c = charAt(p, lines);
                if (!checked.contains(p) && !inside.contains(p) && c == startChar) {
                    queue.add(p);
                }

                checked.add(p);
            }
        }

        return inside;
    }

    private char charAt(Point p, List<String> lines) {
        if (p.y >= 0 && p.y < lines.size() && p.x >= 0 && p.x < lines.get(p.y).length()) {
            return lines.get(p.y).charAt(p.x);
        } else {
            return ' ';
        }
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
