package com.mgellert.adventofcode.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class BridgeRepair {

    public Long sumValidCalibrations(List<String> lines) {
        List<Equation> equations = lines.stream().map(this::parseLines).toList();

        return equations.stream().map(this::isPossible)
                .filter(Objects::nonNull)
                .mapToLong(r -> r)
                .sum();
    }

    private Long isPossible(Equation equation) {
        if (equation.operands.size() == equation.operators.size() + 1) {
            if (equation.result == equation.evaluate()) {
                return equation.result;
            } else {
                return null;
            }
        }

        var a = isPossible(equation.addOperator('+'));
        var b = isPossible(equation.addOperator('*'));

        if (a != null) {
            return a;
        } else if (b != null) {
            return b;
        } else {
            return null;
        }
    }

    private Equation parseLines(String line) {
        String[] split = line.split(": ");
        var result = Long.parseLong(split[0]);
        var operands = Arrays.stream(split[1].split(" ")).map(Long::parseLong).toList();
        return new Equation(result, operands, new ArrayList<>());
    }

    private static final class Equation {
        final long result;
        final List<Long> operands;
        final List<Character> operators;

        public Equation(long result, List<Long> operands, List<Character> operators) {
            this.result = result;
            this.operands = operands;
            this.operators = operators;
        }

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
