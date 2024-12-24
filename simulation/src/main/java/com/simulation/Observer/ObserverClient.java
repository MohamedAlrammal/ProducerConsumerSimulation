package com.simulation.Observer;

import com.simulation.Objects.Queue;

import java.util.List;

public class ObserverClient {
    public void updatePreQueues(List<Queue> queueList){
        ProductStation productStation=new ProductStation();
        PreviousQueues previousQueues =new PreviousQueues();
        productStation.addObserverQueue(previousQueues);
        productStation.SetNumofProduct(-1);
    }
    public void updateFollQueues(List<Queue> queueList){
        ProductStation productStation=new ProductStation();
        FollwersQueues follwersQueues =new FollwersQueues();
        productStation.addObserverQueue(follwersQueues);
        productStation.SetNumofProduct(1);
    }
}
