package com.github.LAnat.undoableStringBuilder;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
public class UndoableStringBuilder {
    private char[] value = new char[16];
    private int count;
    private Deque<UndoableStringBuilderSnapshot> history = new ArrayDeque<>();

    public UndoableStringBuilder() {
        saveSnapshot(); //for all constructors
    }

    //some methods

    public UndoableStringBuilder undo() {
        if (history.size() <= 1) {
            return this;
        }

        history.pop();
        UndoableStringBuilderSnapshot previous = history.peek();
        restoreFromSnapshot(previous);

        return this;
    }

    private void saveSnapshot() {
        history.push(new UndoableStringBuilderSnapshot(this));
    }

    private void restoreFromSnapshot(UndoableStringBuilderSnapshot snapshot) {
        this.value = snapshot.getValue();
        this.count = snapshot.getCount();
    }
}
