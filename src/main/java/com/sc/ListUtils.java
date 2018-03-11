package com.sc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static List<Integer> stringToInt(List<String> input) {
        try {
            return input.stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.<Integer>toList());

        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }
}
