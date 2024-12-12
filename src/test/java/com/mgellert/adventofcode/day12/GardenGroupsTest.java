package com.mgellert.adventofcode.day12;

import com.mgellert.adventofcode.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GardenGroupsTest {

    private final GardenGroups gardenGroups = new GardenGroups();

    @Test
    void testFencingPrice() {
        assertEquals(1930, gardenGroups.fencingPrice(testInput));
    }

    @Test
    void solveDay12Part1() {
        assertEquals(1486324, gardenGroups.fencingPrice(input));
    }

    @Test
    void testFencingPriceWithDiscount() {
        assertEquals(1206, gardenGroups.fencingPriceWithDiscount(testInput));
    }

    @Test
    void solveDay12Part2() {
        assertEquals(898684, gardenGroups.fencingPriceWithDiscount(input));
    }

    private final List<String> testInput = Arrays.stream("""
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """.split("\\n")).toList();

    private final List<String> input = FileReader.readLines("day12");


}