package com.mgellert.adventofcode.day01;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistorianHysteriaTest {

    private final HistorianHysteria historianHysteria = new HistorianHysteria();

    @Test
    public void testFindTotalDistance() {
        assertEquals(11, historianHysteria.findTotalDistance(testInput));
    }

    @Test
    public void testSolveDay1Part1() {
        assertEquals(1873376, historianHysteria.findTotalDistance(input));
    }

    @Test
    public void testCalculateSimilarityScore() {
        assertEquals(31, historianHysteria.calculateSimilarityScore(testInput));
    }

    @Test
    public void testSolveDay1Part2() {
        assertEquals(18997088, historianHysteria.calculateSimilarityScore(input));
    }

    private static final List<String> testInput = Arrays.stream("""
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """.split("\n")).toList();

    private static final List<String> input = FileReader.readLines("day01");
}