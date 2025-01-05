package com.simulation.SnapShot;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private final List<memento> mementoList = new ArrayList<>();

    public void add(memento state) {
        mementoList.add(state);
    }

    public memento getMementoList() {
        if (mementoList.isEmpty()) {
            throw new IllegalStateException("No mementos available.");
        }
        return mementoList.getFirst();
    }
}
