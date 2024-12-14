package com.mgellert.adventofcode.day14;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestroomRedoubtTest {

    private final RestroomRedoubt restroomRedoubt = new RestroomRedoubt();

    @Test
    void testSafetyFactor() {
        assertEquals(12, restroomRedoubt.safetyFactor(testInput, 100, 11, 7));
    }

    @Test
    void solveDay14Part1() {
        assertEquals(211692000, restroomRedoubt.safetyFactor(input, 100, 101, 103));
    }

    @Test
    void solveDay14Part2() {
        assertEquals(6587, restroomRedoubt.findChristmasTree(input, 101, 103));
    }

    private final List<String> testInput = Arrays.stream("""
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
            """.split("\\n")).toList();

    private final List<String> input = FileReader.readLines("day14");
}