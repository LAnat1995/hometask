package com.github.LAnat.undoableStringBuilder;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class UndoableStringBuilderSnapshot {
    private final char[] value;
    private final int count;

    public UndoableStringBuilderSnapshot(UndoableStringBuilder builder) {
        this.value = Arrays.copyOf(builder.getValue(), builder.getCount());
        this.count = builder.getCount();
    }
}
