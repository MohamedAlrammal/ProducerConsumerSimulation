package com.simulation.QueueObserver;

import com.simulation.Objects.Queue;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;
import com.simulation.returnObjects.returnQueue;

import java.util.List;

public class FollwersQueues implements Observer {

    private List<Queue> Follwer;
    ReturnQueues returnQueues =ReturnQueues.getInstance();
    private List<returnQueue>returnQueueList=returnQueues.getQueues();
    ReturnMacines returnMacines =ReturnMacines.getInstance();

    public void setFollwer(List<Queue> Follwer) {
        this.Follwer=Follwer;
    }

    @Override
    public void update(int numOfProduct) {
        for (Queue q : Follwer) {
            q.setNoofProducts(q.getNoofProducts() + numOfProduct);
            for(returnQueue r:returnQueueList){
                if(q.getId().equals(r.getId()))
                    r.setNoOfPruducts(r.getNoOfPruducts()+numOfProduct);
            }
        }
    }
}