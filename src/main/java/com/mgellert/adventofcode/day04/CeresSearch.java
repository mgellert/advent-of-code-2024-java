package com.mgellert.adventofcode.day04;

import java.util.List;

public class CeresSearch {

    public long countXMAS(List<String> lines) {
        var count = 0L;

        // horizontal -
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length() - 3; j++) {
                var a = lines.get(i).charAt(j);
                var b = lines.get(i).charAt(j + 1);
                var c = lines.get(i).charAt(j + 2);
                var d = lines.get(i).charAt(j + 3);

                if (isXMAS(a, b, c, d) || isXMAS(d, c, b, a)) {
                    count++;
                }
            }
        }

        // vertical |
        for (int i = 0; i < lines.size() - 3; i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                var a = lines.get(i).charAt(j);
                var b = lines.get(i + 1).charAt(j);
                var c = lines.get(i + 2).charAt(j);
                var d = lines.get(i + 3).charAt(j);

                if (isXMAS(a, b, c, d) || isXMAS(d, c, b, a)) {
                    count++;
                }
            }
        }

        // diagonal \
        for (int i = 0; i < lines.size() - 3; i++) {
            for (int j = 0; j < lines.get(i).length() - 3; j++) {
                var a = lines.get(i).charAt(j);
                var b = lines.get(i + 1).charAt(j + 1);
                var c = lines.get(i + 2).charAt(j + 2);
                var d = lines.get(i + 3).charAt(j + 3);

                if (isXMAS(a, b, c, d) || isXMAS(d, c, b, a)) {
                    count++;
                }
            }
        }

        // diagonal /
        for (int i = 0; i < lines.size() - 3; i++) {
            for (int j = 3; j < lines.get(i).length(); j++) {
                var a = lines.get(i).charAt(j);
                var b = lines.get(i + 1).charAt(j - 1);
                var c = lines.get(i + 2).charAt(j - 2);
                var d = lines.get(i + 3).charAt(j - 3);

                if (isXMAS(a, b, c, d) || isXMAS(d, c, b, a)) {
                    count++;
                }
            }
        }

        return count;
    }

    public long countX_MAS(List<String> lines) {
        var count = 0L;

        for (int i = 0; i < lines.size() - 2; i++) {
            for (int j = 0; j < lines.get(i).length() - 2; j++) {
                var a = lines.get(i).charAt(j);
                var b = lines.get(i).charAt(j + 2);
                var c = lines.get(i + 1).charAt(j + 1);
                var d = lines.get(i + 2).charAt(j);
                var e = lines.get(i + 2).charAt(j + 2);
                if (isX_MAS(a, b, c, d, e)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isXMAS(char a, char b, char c, char d) {
        return a == 'X' && b == 'M' && c == 'A' && d == 'S';
    }

    private boolean isX_MAS(char a, char b, char c, char d, char e) {
        return c == 'A' && (a == 'M' && e == 'S' || a == 'S' && e == 'M') && (b == 'M' && d == 'S' || b == 'S' && d == 'M');
    }
}
