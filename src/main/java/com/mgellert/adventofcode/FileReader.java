package com.mgellert.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FileReader {

    public static List<String> readLines(String day) {
        try {
            return Files.readAllLines(getPath(day)).stream()
                    .filter(Objects::nonNull)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + day);
        }
    }

    public static List<Long> readLinesAsLong(String day) {
        return readLines(day).stream().map(Long::parseLong).toList();
    }

    public static boolean exists(String day) {
        return Files.exists(getPath(day));
    }

    private static Path getPath(String day) {
        return Path.of("src/main/resources/inputs", day);
    }
}
