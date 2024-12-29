package com.simulation.SnapShot;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    private List<memento>MementoList=new ArrayList<>();

    public void add(memento state){
        MementoList.add(state);
    }
    public memento getMementoList(int index) {
        return MementoList.get(index);
    }
}
