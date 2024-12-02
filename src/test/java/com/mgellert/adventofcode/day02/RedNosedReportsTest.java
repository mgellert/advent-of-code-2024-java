package com.mgellert.adventofcode.day02;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedNosedReportsTest {

    private final RedNosedReports redNosedReports = new RedNosedReports();

    @Test
    public void testCountSafeReports() {
        assertEquals(2, redNosedReports.countSafeReports(testInput));
    }

    @Test
    public void testSolveDay2Part1() {
        assertEquals(686, redNosedReports.countSafeReports(input));
    }

    @Test
    public void testCountSafeReportsUsingDampener() {
        assertEquals(4, redNosedReports.useProblemDampener(testInput));
    }

    @Test
    public void testSolveDay2Part2() {
        assertEquals(717, redNosedReports.useProblemDampener(input));
    }

    private static final List<String> testInput = Arrays.stream("""
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """.split("\\n")).toList();

    private static final List<String> input = FileReader.readLines("day02");

}