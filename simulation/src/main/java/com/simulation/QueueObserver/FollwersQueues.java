package com.simulation.QueueObserver;

import com.simulation.Objects.Products;
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
    public void update(int numOfProduct,Products products) {

        for (Queue q : Follwer) {
            if(q.getProduct().isEmpty()||!q.getProduct().contains(products)){
                q.setNoofProducts(q.getNoofProducts() + numOfProduct);
                q.setProduct(products);
              for(returnQueue r:returnQueueList){
                if(q.getId().equals(r.getId()))
                    r.setNoOfPruducts(r.getNoOfPruducts()+numOfProduct);
            }
        }
        }
    }
}