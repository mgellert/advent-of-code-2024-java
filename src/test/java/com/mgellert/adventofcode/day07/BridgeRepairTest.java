package com.mgellert.adventofcode.day07;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BridgeRepairTest {

    private final BridgeRepair bridgeRepair = new BridgeRepair();

    @Test
    void testSumValidCalibrations() {
        assertEquals(3749, bridgeRepair.sumValidCalibrations(testInput));
    }

    @Test
    void solveDay7part1() {
        assertEquals(5540634308362L, bridgeRepair.sumValidCalibrations(input));
    }

    private final List<String> testInput = Arrays.stream("""
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """.split("\\n")).toList();

    private final List<String> input = FileReader.readLines("day07");

}