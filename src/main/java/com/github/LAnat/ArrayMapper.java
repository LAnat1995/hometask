package com.github.LAnat;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.UnaryOperator;

public class ArrayMapper {
    @SuppressWarnings("unchecked")
    public static <T> T[] arrayMapping(T[] array, UnaryOperator<T> mapper) {
        if (array == null || mapper == null) {
            throw new NullPointerException("Array and function must not be null");
        }

        return (T[]) Arrays.stream(array)
                .map(mapper)
                .toArray(size -> (T[]) Array.newInstance(
                        array.getClass().getComponentType(),
                        size
                ));
    }
}
