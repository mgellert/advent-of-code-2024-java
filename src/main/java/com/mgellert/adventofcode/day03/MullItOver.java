package com.mgellert.adventofcode.day03;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {

    private final Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
    private final Pattern patternWithMoreOps = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");

    public long sumOfMultiplications(List<String> lines) {
        var sum = 0L;
        var line = String.join("", lines);

        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            var a = Long.parseLong(matcher.group(1));
            var b = Long.parseLong(matcher.group(2));
            sum += a * b;
        }

        return sum;
    }

    public long somOfEnabledMultiplications(List<String> lines) {
        var line = String.join("", lines);

        List<String> operations = new LinkedList<>();
        Matcher matcher = patternWithMoreOps.matcher(line);
        while (matcher.find()) {
            operations.add(matcher.group(0));
        }

        var sum = 0L;
        var enabled = true;

        for (String operation : operations) {
            if (operation.equals("do()")) {
                enabled = true;
            } else if (operation.equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                Matcher matcher1 = pattern.matcher(operation);
                if (matcher1.find()) {
                    var a = Long.parseLong(matcher1.group(1));
                    var b = Long.parseLong(matcher1.group(2));
                    sum += a * b;
                }
            }
        }

        return sum;
    }
}
