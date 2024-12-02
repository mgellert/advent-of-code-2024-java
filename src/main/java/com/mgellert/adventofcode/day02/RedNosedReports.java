package com.mgellert.adventofcode.day02;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RedNosedReports {

    public long useProblemDampener(List<String> reports) {
        return reports.stream()
                .map(this::parse)
                .filter(this::isDampenedSafe)
                .count();
    }

    public long countSafeReports(List<String> reports) {
        return reports.stream()
                .map(this::parse)
                .filter(this::isSafe)
                .count();
    }

    private boolean isDampenedSafe(List<Integer> report) {
        if (isSafe(report)) return true;

        for (int i = 0; i < report.size(); i++) {
            var dampenedList = new LinkedList<>(report);
            dampenedList.remove(i);
            if (isSafe(dampenedList)) return true;
        }

        return false;
    }

    private boolean isSafe(List<Integer> report) {

        Direction direction = null;

        for (int i = 0; i < report.size() - 1; i++) {
            var a = report.get(i);
            var b = report.get(i + 1);
            var diff = b - a;

            if (direction == null) {
                if (diff > 0) direction = Direction.INCREASING;
                else if (diff < 0) direction = Direction.DECREASING;
                else return false; // a == b is unsafe
            } else if (direction == Direction.INCREASING) {
                if (diff < 0) return false;
            } else {
                if (diff > 0) return false;
            }

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) return false;
        }

        return true;
    }

    private List<Integer> parse(String line) {
        return Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
    }

    private enum Direction {
        INCREASING, DECREASING
    }
}
