package com.mgellert.adventofcode.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class WarehouseWoes {

    private static final Set<Character> MOVEMENTS = Set.of('^', '>', 'v', '<');

    public long sumOfCoordinates(List<String> lines) {
        List<String> map = lines.stream().filter(line -> line.startsWith("#")).toList();

        String movements = lines.stream()
                .filter(line -> !line.isEmpty())
                .filter(line -> MOVEMENTS.contains(line.charAt(0)))
                .collect(Collectors.joining());

        Thing robot = parseRobot(map);
        Set<Thing> boxes = parseBoxes(map);
        Set<Thing> walls = parseWalls(map);

        for (char movement : movements.toCharArray()) {
            var direction = Direction.fromMovement(movement);
            move(robot, direction, walls, boxes);
        }

        return gpsScore(boxes);
    }

    public long sumOfWideMap(List<String> lines) {
        List<String> map = lines.stream()
                .filter(line -> line.startsWith("#"))
                .map(WarehouseWoes::doubleLine)
                .toList();

        String movements = lines.stream()
                .filter(line -> !line.isEmpty())
                .filter(line -> MOVEMENTS.contains(line.charAt(0)))
                .collect(Collectors.joining());

        Thing robot = parseRobot(map);
        Set<Thing> boxes = parseWideBoxes(map);
        Set<Thing> walls = parseWalls(map);

//        paint(robot, walls, boxes, map);

        for (char movement : movements.toCharArray()) {
            var direction = Direction.fromMovement(movement);
            boolean canMove = canMoveWide(robot, direction, walls, boxes);
            if (canMove) {
                moveWide(robot, direction, walls, boxes);
//                paint(robot, walls, boxes, map);
            }
        }

        return gpsScore(boxes);
    }

    private boolean canMoveWide(Thing thing, Direction direction, Set<Thing> walls, Set<Thing> boxes) {
        List<Point> newPositions = new ArrayList<>();
        if (thing.type == Type.ROBOT) {
            newPositions.add(thing.point.plus(direction.delta));
        } else {
            newPositions.add(thing.point.plus(direction.delta));
            newPositions.add(thing.point.plus(Direction.RIGHT.delta).plus(direction.delta));
        }

        var wallPositions = walls.stream().map(it -> it.point).collect(Collectors.toSet());
        Map<Point, Thing> boxPositions = new HashMap<>();
        for (Thing box : boxes) {
            if (!box.equals(thing)) {
                boxPositions.put(box.point, box);
                boxPositions.put(box.point.plus(Direction.RIGHT.delta), box);
            }
        }

        if (wallPositions.stream().noneMatch(newPositions::contains) && boxPositions.keySet().stream().noneMatch(newPositions::contains)) {
            return true;
        }

        if (wallPositions.stream().anyMatch(newPositions::contains)) {
            return false;
        }

        Set<Thing> connected = new HashSet<>();
        for (Point p : newPositions) {
            if (boxPositions.containsKey(p)) {
                connected.add(boxPositions.get(p));
            }
        }

        return connected.stream().allMatch(it -> canMoveWide(it, direction, walls, boxes));
    }

    private void moveWide(Thing thing, Direction direction, Set<Thing> walls, Set<Thing> boxes) {
        List<Point> newPositions = new ArrayList<>();
        if (thing.type == Type.ROBOT) {
            newPositions.add(thing.point.plus(direction.delta));
        } else {
            newPositions.add(thing.point.plus(direction.delta));
            newPositions.add(thing.point.plus(Direction.RIGHT.delta).plus(direction.delta));
        }

        Map<Point, Thing> boxPositions = new HashMap<>();
        for (Thing box : boxes) {
            if (!box.equals(thing)) {
                boxPositions.put(box.point, box);
                boxPositions.put(box.point.plus(Direction.RIGHT.delta), box);
            }
        }

        Set<Thing> connected = new HashSet<>();
        for (Point p : newPositions) {
            if (boxPositions.containsKey(p)) {
                connected.add(boxPositions.get(p));
            }
        }

        for (Thing t : connected) {
            moveWide(t, direction, walls, boxes);
        }
        thing.point = newPositions.getFirst();
    }

    private void paint(Thing robot, Set<Thing> walls, Set<Thing> boxes, List<String> map) {
        var wallPositions = walls.stream().map(it -> it.point).collect(Collectors.toSet());
        var boxPositions = boxes.stream().map(it -> it.point).collect(Collectors.toSet());

        for (int i = 0; i < map.size(); i++) {
            var box = false;
            for (int j = 0; j < map.getFirst().length(); j++) {
                var p = new Point(j, i);
                if (robot.point.equals(p)) {
                    System.out.print("@");
                } else if (wallPositions.contains(p)) {
                    System.out.print("#");
                } else if (boxPositions.contains(p)) {
                    System.out.print("[");
                    box = true;
                } else if (box) {
                    System.out.print("]");
                    box = false;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private long gpsScore(Set<Thing> boxes) {
        return boxes.stream().map(it -> it.point).mapToLong(it -> 100L * it.y + it.x).sum();
    }

    private Set<Thing> parseWideBoxes(List<String> map) {
        Set<Thing> boxes = new HashSet<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (map.get(i).charAt(j) == '[') {
                    boxes.add(new Thing(new Point(j, i), Type.BOX));
                }
            }
        }
        return boxes;
    }

    private static String doubleLine(String line) {
        var doubled = new StringBuilder();
        for (char c : line.toCharArray()) {
            switch (c) {
                case '#' -> doubled.append("##");
                case '@' -> doubled.append("@.");
                case 'O' -> doubled.append("[]");
                default -> doubled.append("..");
            }
        }
        return doubled.toString();
    }

    private boolean move(Thing thing, Direction direction, Set<Thing> walls, Set<Thing> boxes) {
        var newPosition = thing.point.plus(direction.delta);
        var wallPositions = walls.stream().map(it -> it.point).collect(Collectors.toSet());
        var boxPositions = boxes.stream().map(it -> it.point).collect(Collectors.toSet());

        if (!wallPositions.contains(newPosition) && !boxPositions.contains(newPosition)) {
            thing.point = newPosition;
            return true;
        }

        if (wallPositions.contains(newPosition)) {
            return false;
        }

        Thing box = boxes.stream().filter(b -> b.point.equals(newPosition)).findFirst()
                .orElseThrow();

        var moved = move(box, direction, walls, boxes);
        if (moved) thing.point = newPosition;
        return moved;
    }

    private Set<Thing> parseWalls(List<String> map) {
        Set<Thing> walls = new HashSet<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (map.get(i).charAt(j) == '#') {
                    walls.add(new Thing(new Point(j, i), Type.WALL));
                }
            }
        }
        return walls;
    }

    private Set<Thing> parseBoxes(List<String> map) {
        Set<Thing> boxes = new HashSet<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (map.get(i).charAt(j) == 'O') {
                    boxes.add(new Thing(new Point(j, i), Type.BOX));
                }
            }
        }
        return boxes;
    }

    private Thing parseRobot(List<String> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (map.get(i).charAt(j) == '@') {
                    return new Thing(new Point(j, i), Type.ROBOT);
                }
            }
        }
        throw new IllegalStateException();
    }

    private enum Type {
        ROBOT, WALL, BOX
    }

    private class Thing {
        private Point point;
        private final Type type;

        public Thing(Point point, Type value) {
            this.point = point;
            this.type = value;
        }

        @Override
        public String toString() {
            return "%s(%s)".formatted(type, point);
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

        static Direction fromMovement(char movement) {
            return switch (movement) {
                case '>' -> Direction.RIGHT;
                case 'v' -> Direction.DOWN;
                case '<' -> Direction.LEFT;
                case '^' -> Direction.UP;
                default -> throw new IllegalStateException();
            };
        }
    }

    private record Point(int x, int y) {
        Point plus(Point other) {
            return new Point(this.x + other.x, this.y + other.y);
        }
    }
}
