package com.simulation.Observer;

import com.simulation.Objects.Queue;

import java.util.List;

public class FollwersQueues implements Observer {

    private List<Queue> Follwer;

    public void setFollwer(List<Queue> Follwer) {
        this.Follwer=Follwer;
    }

    @Override
    public void update(int numOfProduct) {
        for (Queue q : Follwer) {
            q.setNoofProducts(q.getNoofProducts() + numOfProduct);
        }
    }
}