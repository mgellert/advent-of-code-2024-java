package com.mgellert.adventofcode.day05;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrintQueueTest {

    private final PrintQueue printQueue = new PrintQueue();

    @Test
    public void testSumOfMiddleOfCorrectUpdates() {
        assertEquals(143, printQueue.sumOfMiddleOfCorrectUpdates(testInput));
    }

    @Test
    public void solveDay5Part1() {
        assertEquals(5588, printQueue.sumOfMiddleOfCorrectUpdates(input));
    }

    @Test
    public void testSumOfMiddleOfCorrectedUpdates() {
        assertEquals(123, printQueue.sumOfMiddleOfCorrectedUpdates(testInput));
    }

    @Test
    public void solveDay5Part2() {
        assertEquals(5331, printQueue.sumOfMiddleOfCorrectedUpdates(input));
    }

    private static final List<String> testInput = Arrays.stream("""
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """.split("\\n")).toList();

    private static final List<String> input = FileReader.readLines("day05");
}