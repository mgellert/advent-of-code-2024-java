package com.mgellert.adventofcode.day11;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlutonianPebblesTest {

    private final PlutonianPebbles plutonianPebbles = new PlutonianPebbles();

    @Test
    void testCountStonesAfterBlinking() {
        assertEquals(55312L, plutonianPebbles.countStonesAfterBlinking(testInput, 25));
    }

    @Test
    void solveDay11Part1() {
        assertEquals(233875L, plutonianPebbles.countStonesAfterBlinking(input, 25));
    }

    @Test
    void solveDay11Part2() {
        assertEquals(277444936413293L, plutonianPebbles.countStonesAfterBlinking(input, 75));
    }


    private final String testInput = "125 17";

    private final String input = FileReader.readLine("day11");

}