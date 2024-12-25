package com.simulation.QueueObserver;

import com.simulation.Objects.Queue;

import java.util.List;

public class ObserverClient {
    public void updatePreQueues(List<Queue> queueList){
        ProductStation productStation=new ProductStation();
        PreviousQueues previousQueues =new PreviousQueues();
        previousQueues.setPrevious(queueList);
        productStation.addObserverQueue(previousQueues);
        productStation.SetNumofProduct(-1);
    }
    public void updateFollQueues(List<Queue> queueList){
        ProductStation productStation=new ProductStation();
        FollwersQueues follwersQueues =new FollwersQueues();
        follwersQueues.setFollwer(queueList);
        productStation.addObserverQueue(follwersQueues);
        productStation.SetNumofProduct(1);
    }
}
