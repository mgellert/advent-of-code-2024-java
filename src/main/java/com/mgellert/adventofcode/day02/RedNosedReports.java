package com.mgellert.adventofcode.day02;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RedNosedReports {

    public long useProblemDampener(List<String> reports) {
        return reports.stream()
                .map(this::parse)
                .filter(this::isSafeWithDampening)
                .count();
    }

    public long countSafeReports(List<String> reports) {
        return reports.stream()
                .map(this::parse)
                .filter(this::isSafe)
                .count();
    }

    private boolean isSafeWithDampening(List<Integer> report) {
        if (isSafe(report)) return true;

        for (int i = 0; i < report.size(); i++) {
            var dampenedList = new LinkedList<>(report);
            dampenedList.remove(i);
            if (isSafe(dampenedList)) return true;
        }

        return false;
    }

    private boolean isSafe(List<Integer> report) {
        Direction direction = getDirection(report);

        for (int i = 0; i < report.size() - 1; i++) {
            var a = report.get(i);
            var b = report.get(i + 1);
            var diff = b - a;

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) return false;

            if (direction == Direction.INCREASING && diff < 0) {
                return false;
            } else if (direction == Direction.DECREASING && diff > 0) {
                return false;
            }
        }

        return true;
    }

    private List<Integer> parse(String line) {
        return Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
    }

    private Direction getDirection(List<Integer> report) {
        var diff = report.get(1) - report.get(0);
        if (diff > 0) return Direction.INCREASING;
        else if (diff < 0) return Direction.DECREASING;
        return null;
    }

    private enum Direction {
        INCREASING, DECREASING
    }
}
