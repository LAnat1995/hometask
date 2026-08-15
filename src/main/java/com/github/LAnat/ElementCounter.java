package com.github.LAnat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ElementCounter {
    public static <T> Map<T, Integer> countElements(T[] array) {
        if (array == null) {
            return new HashMap<>();
        }

        return Arrays.stream(array)
                .collect(Collectors.toMap(Function.identity(), element -> 1, Integer::sum));
    }
}
