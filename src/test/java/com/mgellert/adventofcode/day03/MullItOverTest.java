package com.mgellert.adventofcode.day03;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MullItOverTest {

    private final MullItOver mullItOver = new MullItOver();

    @Test
    void testSumOfMultiplications() {
        assertEquals(161, mullItOver.sumOfMultiplications(testInput));
    }

    @Test
    void solveDay3Part1() {
        assertEquals(169021493, mullItOver.sumOfMultiplications(input));
    }

    @Test
    void testSumOfEnabledMultiplications() {
        assertEquals(48, mullItOver.somOfEnabledMultiplications(testInput2));
    }

    @Test
    void solveDay3Part2() {
        assertEquals(111762583, mullItOver.somOfEnabledMultiplications(input));
    }


    private static final List<String> testInput = List.of(
            "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    );

    private static final List<String> testInput2 = List.of(
            "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    );

    private static final List<String> input = FileReader.readLines("day03");

}