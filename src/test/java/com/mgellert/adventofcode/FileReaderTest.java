package com.mgellert.adventofcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReaderTest {

    @Test
    void shouldReadLinesFromFile() {
        List<String> testInput = FileReader.readLines("day01");
        assertEquals(1000, testInput.size());
    }

    @Test
    void shouldReadLongsFromFile() {
        List<Long> testInput = FileReader.readLinesAsLong("testinput");
        Long sum = testInput.stream().mapToLong(a -> a).sum();
        assertEquals(6, sum);
    }

    @Test
    void shouldTestExists() {
        assertTrue(FileReader.exists("day01"));
        assertFalse(FileReader.exists("foobar"));
    }
}