package com.mgellert.adventofcode.day13;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClawContraptionTest {

    private final ClawContraption clawContraption = new ClawContraption();

    @Test
    void testFewestTokensToSpend() {
        assertEquals(480, clawContraption.fewestTokensToSpend(testInput));
    }

    @Test
    void solveDay13Part1() {
        assertEquals(40369, clawContraption.fewestTokensToSpend(input));
    }

    private final List<List<String>> testInput = Arrays.stream("""
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
            
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
            
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
            
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
            """.split("\\n\\n")).map(b -> Arrays.stream(b.split("\\n")).toList()).toList();

    private final List<List<String>> input = FileReader.readBlocks("day13");

}