package com.simulation.SnapShot;

import com.simulation.Objects.ObjectsRequest;
import com.simulation.returnObjects.ObjectsAnswer;

public class SnapShotClient {
    public void replay(ObjectsRequest state){
        Orginator orginator =new Orginator();
        CareTaker careTaker =new CareTaker();
        orginator.setState(state);
        careTaker.add(orginator.SaveToMemento());
    }
}
