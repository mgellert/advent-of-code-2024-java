package com.mgellert.adventofcode.day08;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResonantCollinearityTest {

    private final ResonantCollinearity resonantCollinearity = new ResonantCollinearity();

    @Test
    void testCountAntinodesOnMap() {
        assertEquals(14, resonantCollinearity.countAntinodesOnMap(testInput));
    }

    @Test
    void solveDay8Part1() {
        assertEquals(336, resonantCollinearity.countAntinodesOnMap(input));
    }

    @Test
    void testCountAntinodesOnMapWithHarmonics() {
        assertEquals(34, resonantCollinearity.countAntinodesOnMapWithHarmonics(testInput));
    }

    @Test
    void solveDay8Part2() {
        assertEquals(1131, resonantCollinearity.countAntinodesOnMapWithHarmonics(input));
    }

    private final List<String> testInput = Arrays.stream("""
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
            """.split("\\n")).toList();

    private final List<String> input = FileReader.readLines("day08");

}