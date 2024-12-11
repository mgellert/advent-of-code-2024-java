package com.mgellert.adventofcode.day11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlutonianPebbles {

    public long countStonesAfterBlinking(String line, int blinks) {

        Map<Long, Long> stones = new HashMap<>();
        for (String s : line.split(" ")) {
            long num = Long.parseLong(s);
            stones.putIfAbsent(num, 0L);
            stones.computeIfPresent(num, (key, count) -> count + 1);
        }

        for (int i = 0; i < blinks; i++) {
            Map<Long, Long> updatedStones = new HashMap<>();
            if (stones.containsKey(0L)) {
                updatedStones.put(1L, stones.get(0L));
            }

            var evenStones = stones.keySet().stream()
                    .filter(n -> n != 0L)
                    .filter(n -> n.toString().length() % 2 == 0)
                    .toList();

            for (Long evenStone : evenStones) {
                var str = evenStone.toString();
                var a = Long.parseLong(str.substring(0, str.length() / 2));
                var b = Long.parseLong(str.substring(str.length() / 2));

                var count = stones.get(evenStone);

                updatedStones.put(a, updatedStones.getOrDefault(a, 0L) + count);
                updatedStones.put(b, updatedStones.getOrDefault(b, 0L) + count);
            }

            var oddStones = stones.keySet().stream()
                    .filter(n -> n != 0L)
                    .filter(n -> n.toString().length() % 2 != 0)
                    .toList();

            for (long oddStone : oddStones) {
                var count = stones.get(oddStone);
                updatedStones.put(oddStone * 2024, updatedStones.getOrDefault(oddStone * 2024, 0L) + count);
            }

            stones = updatedStones;
        }

        return stones.values().stream().mapToLong(n -> n).sum();
    }
}
