package com.simulation.SnapShot;

import com.simulation.Objects.ObjectsRequest;
import com.simulation.returnObjects.ObjectsAnswer;

public class memento {
    private final ObjectsRequest state;

    public memento(ObjectsRequest state) {
        this.state = state;
    }

    public ObjectsRequest getState() {
        return state;
    }
}
