package com.simulation.SnapShot;

import com.simulation.Objects.ObjectsRequest;
import com.simulation.returnObjects.ObjectsAnswer;

public class Orginator {
    private ObjectsRequest state;

    public ObjectsRequest getState() {
        return state;
    }

    public void setState(ObjectsRequest state) {
        this.state = state;
    }
    public memento SaveToMemento(){
        return new memento(state);
    }
    public void getStateFromMemento(memento memento){
        state=memento.getState();
    }
}
