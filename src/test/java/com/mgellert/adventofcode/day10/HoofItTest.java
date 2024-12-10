package com.mgellert.adventofcode.day10;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HoofItTest {

    private final HoofIt hoofIt = new HoofIt();

    @Test
    void testSumOfTrailHeadScores() {
        assertEquals(36, hoofIt.sumOfTrailHeadScores(testInput));
    }

    @Test
    void solveDay10Part1() {
        assertEquals(796, hoofIt.sumOfTrailHeadScores(input));
    }

    @Test
    void testSumOfTrailHeadRates() {
        assertEquals(81, hoofIt.sumOfTrailHeadRates(testInput));
    }

    @Test
    void solveDay10Part2() {
        assertEquals(1942, hoofIt.sumOfTrailHeadRates(input));
    }

    private final List<String> testInput = Arrays.stream("""
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """.split("\\n")).toList();

    private final List<String> input = FileReader.readLines("day10");

}