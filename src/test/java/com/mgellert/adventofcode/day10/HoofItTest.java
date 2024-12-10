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
        assertEquals(4, hoofIt.sumOfTrailHeadScores(testInput));
    }


    private final List<String> testInput = Arrays.stream("""
..90..9
...1.98
...2..7
6543456
765.987
876....
987....
            """.split("\\n")).toList();

    private final List<String> input = FileReader.readLines("day10");

}