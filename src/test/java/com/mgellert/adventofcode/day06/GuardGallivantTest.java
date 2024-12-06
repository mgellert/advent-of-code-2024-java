package com.mgellert.adventofcode.day06;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuardGallivantTest {

    private final GuardGallivant guardGallivant = new GuardGallivant();

    @Test
    void testCountGuardPositions() {
        assertEquals(41, guardGallivant.countGuardPositions(testInput));
    }

    @Test
    void solveDay6Part1() {
        assertEquals(4982, guardGallivant.countGuardPositions(input));
    }

    @Test
    void testCountPossibleObstacles() {
        assertEquals(6, guardGallivant.countPossibleObstacles(testInput));
    }

    @Test
    void solveDay6Part2() {
        assertEquals(1663, guardGallivant.countPossibleObstacles(input));
    }

    private static final List<String> testInput = Arrays.stream("""
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """.split("\\n")).toList();

    private static final List<String> input = FileReader.readLines("day06");
}