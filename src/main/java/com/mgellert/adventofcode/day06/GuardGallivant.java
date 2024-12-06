package com.mgellert.adventofcode.day06;

import java.util.HashSet;
import java.util.List;

public class GuardGallivant {

    public long countGuardPositions(List<String> lines) {
        var obstacles = new HashSet<Point>();
        var guardPos = new Point(-1, -1);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var c = lines.get(i).charAt(j);
                if (c == '#') {
                    obstacles.add(new Point(j, i));
                } else if (c == '^') {
                    guardPos = new Point(j, i);
                }
            }
        }

        var visited = new HashSet<Point>();
        var mapSize = lines.size();
        var direction = 0;

        while (isInBounds(guardPos, mapSize)) {
            visited.add(guardPos);

            var delta = Direction.values()[direction % Direction.values().length].delta;
            var newPos = new Point(guardPos.x + delta.x, guardPos.y + delta.y);
            if (obstacles.contains(newPos)) {
                direction++;
            } else {
                guardPos = newPos;
            }
        }

        return visited.size();
    }

    public long countPossibleObstacles(List<String> lines) {
        var obstacles = new HashSet<Point>();
        var guardPos = new Point(-1, -1);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var c = lines.get(i).charAt(j);
                if (c == '#') {
                    obstacles.add(new Point(j, i));
                } else if (c == '^') {
                    guardPos = new Point(j, i);
                }
            }
        }

        var visited = new HashSet<Point>();
        var mapSize = lines.size();
        var direction = 0;
        var initialGuardPos = guardPos;

        while (isInBounds(guardPos, mapSize)) {
            visited.add(guardPos);

            var delta = Direction.values()[direction % Direction.values().length].delta;
            var newPos = new Point(guardPos.x + delta.x, guardPos.y + delta.y);
            if (obstacles.contains(newPos)) {
                direction++;
            } else {
                guardPos = newPos;
            }
        }

        visited.remove(initialGuardPos);
        var goodObs = 0L;

        for (Point p : visited) {
            var addedObstacles = new HashSet<>(obstacles);
            addedObstacles.add(p);
            guardPos = initialGuardPos;
            direction = 0;
            var visited2 = new HashSet<Point>();
            var consecutiveVisited = 0;

            while (isInBounds(guardPos, mapSize)) {
                var delta = Direction.values()[direction % 4].delta;
                var newPos = new Point(guardPos.x + delta.x, guardPos.y + delta.y);

                visited2.add(guardPos);

                if (addedObstacles.contains(newPos)) {
                    direction++;
                } else {
                    guardPos = newPos;
                }

                if (visited2.contains(guardPos)) {
                    consecutiveVisited++;
                } else {
                    consecutiveVisited = 0;
                }

                if (consecutiveVisited > 150) {
                    goodObs++;
                    break;
                }
            }
        }

        return goodObs;
    }

    private boolean isLoop(Point guardPos, int direction, Point initialGuardPos) {
        return guardPos.equals(initialGuardPos) && direction % 4 == 0;
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

    private boolean isInBounds(Point guard, int mapSize) {
        return guard.x >= 0 && guard.x < mapSize && guard.y >= 0 && guard.y < mapSize;
    }

    private record Point(int x, int y) {
        Point plus(Point other) {
            return new Point(this.x + other.x, this.y + other.y);
        }
    }
}
