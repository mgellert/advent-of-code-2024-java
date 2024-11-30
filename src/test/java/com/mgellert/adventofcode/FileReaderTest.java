package com.mgellert.adventofcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    @Test
    void shouldReadLinesFromFile() {
        List<String> testInput = FileReader.readLines("testinput");
        assertEquals(3, testInput.size());
    }

    @Test
    void shouldReadLongsFromFile() {
        List<Long> testInput = FileReader.readLinesAsLong("testinput");
        Long sum = testInput.stream().mapToLong(a -> a).sum();
        assertEquals(6, sum);
    }
}