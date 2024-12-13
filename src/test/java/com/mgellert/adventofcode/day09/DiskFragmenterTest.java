package com.mgellert.adventofcode.day09;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiskFragmenterTest {

    private final DiskFragmenter diskFragmenter = new DiskFragmenter();

    @Test
    void testChecksum() {
        assertEquals(1928, diskFragmenter.checksum(testInput));
    }

    @Test
    void solveDay9Part1() {
        assertEquals(6463499258318L, diskFragmenter.checksum(input));
    }

    private final String testInput = "2333133121414131402";

    private final String input = FileReader.readLine("day09");
}