package com.mgellert.adventofcode.day09;

import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter {

    public long checksum(String input) {
        List<Part> parts = createFilesystem(input);
        compact(parts);
        return checksum(parts);
    }

    private void compact(List<Part> parts) {
        var start = 0;
        var end = parts.size() - 1;

        while (start < end) {
            // seek the next empty space
            while (parts.get(start).id != null && start < end) {
                start++;
            }

            if (start < end) {
                parts.get(start).id = parts.get(end).id;
                parts.removeLast();
                end--;
            }
        }
    }

    private long checksum(List<Part> parts) {
        long sum = 0L;
        for (int i = 0; i < parts.size(); i++) {
            sum += parts.get(i).id * i;
        }
        return sum;
    }

    private List<Part> createFilesystem(String input) {
        List<Part> parts = new ArrayList<>(input.length());
        int id = 0;

        for (int i = 0; i < input.length(); i++) {
            var value = Integer.parseInt(Character.toString(input.charAt(i)));
            if (i % 2 == 0) {
                for (int n = 0; n < value; n++) {
                    parts.add(new Part(id));
                }
                id++;
            } else {
                for (int n = 0; n < value; n++) {
                    parts.add(new Part(null));
                }
            }
        }

        return parts;
    }

    private static class Part {
        private Integer id;

        private Part(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Part{id=%d}".formatted(id);
        }
    }
}
