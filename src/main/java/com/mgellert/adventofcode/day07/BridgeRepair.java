package com.mgellert.adventofcode.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BridgeRepair {

    public Long sumValidCalibrations(List<String> lines) {
        List<Equation> equations = lines.stream().map(this::parseLines).toList();

        return equations.stream().map(equation -> isPossible(equation, false))
                .filter(Objects::nonNull)
                .mapToLong(r -> r)
                .sum();
    }

    public Long sumCalibrations(List<String> lines) {
        List<Equation> equations = lines.stream().map(this::parseLines).toList();

        return equations.stream().map(equation -> isPossible(equation, true))
                .filter(Objects::nonNull)
                .mapToLong(r -> r)
                .sum();
    }

    private Long isPossible(Equation equation, boolean useExtraOperator) {
        if (equation.operands.size() == equation.operators.size() + 1) {
            if (equation.result == equation.evaluate()) {
                return equation.result;
            } else {
                return null;
            }
        }

        if (equation.evaluate() > equation.result) {
            return null;
        }

        var a = isPossible(equation.addOperator('+'), useExtraOperator);
        var b = isPossible(equation.addOperator('*'), useExtraOperator);
        Long c = null;
        if (useExtraOperator) {
            c = isPossible(equation.addOperator('|'), useExtraOperator);
        }

        if (a != null) {
            return a;
        } else if (b != null) {
            return b;
        } else return c;
    }

    private Equation parseLines(String line) {
        String[] split = line.split(": ");
        var result = Long.parseLong(split[0]);
        var operands = Arrays.stream(split[1].split(" ")).map(Long::parseLong).toList();
        return new Equation(result, operands, new ArrayList<>());
    }

    private record Equation(long result, List<Long> operands, List<Character> operators) {
        public long evaluate() {
            var result = operands.getFirst();
            for (int i = 0; i < operators.size(); i++) {
                var operator = operators.get(i);
                if (operator == '+') {
                    result += operands.get(i + 1);
                } else if (operator == '*') {
                    result *= operands.get(i + 1);
                } else { // operator ||
                    result = Long.parseLong(result.toString() + operands.get(i + 1));
                }
            }
            return result;
        }

        public Equation addOperator(Character operator) {
            var o = new ArrayList<>(this.operators);
            o.add(operator);
            return new Equation(this.result, this.operands, o);
        }
    }
}
