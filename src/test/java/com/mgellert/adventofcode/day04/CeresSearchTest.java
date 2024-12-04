package com.mgellert.adventofcode.day04;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CeresSearchTest {

    private final CeresSearch ceresSearch = new CeresSearch();

    @Test
    void testCountXMAS() {
        assertEquals(18, ceresSearch.countXMAS(testInput));
    }

    @Test
    void solveDay4Part1() {
        assertEquals(2464, ceresSearch.countXMAS(input));
    }

    @Test
    void testCountX_MAS() {
        assertEquals(9, ceresSearch.countX_MAS(testInput));
    }

    @Test
    void solveDay4Part2() {
        assertEquals(1982, ceresSearch.countX_MAS(input));
    }

    private static final List<String> testInput = Arrays.stream("""
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """.split("\n")).toList();

    private static final List<String> input = FileReader.readLines("day04");
}