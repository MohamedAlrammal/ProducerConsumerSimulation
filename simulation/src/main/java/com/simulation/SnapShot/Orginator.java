package com.simulation.SnapShot;

import com.simulation.Objects.ObjectsRequest;
import com.simulation.returnObjects.ObjectsAnswer;

public class Orginator {
    private ObjectsRequest state;

    public ObjectsRequest getState() {
        return state;
    }

    public void setState(ObjectsRequest state) {
        this.state =state;
        System.out.println(state.getQueues().size()+" "+state.getQueues().get(0).getNoofProducts()+"  "+state.getQueues().get(1).getNoofProducts());
    }
    public memento SaveToMemento(){
        return new memento(state);
    }
    public void getStateFromMemento(memento memento){
        state=memento.getState();
        System.out.println(memento.getState().getQueues().get(0)+"      "+memento.getState().getQueues().get(1));
    }
}
