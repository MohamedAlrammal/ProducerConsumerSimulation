package com.simulation.Observer;

import com.simulation.Objects.Queue;

import java.util.List;

public class PreviousQueues implements Observer{
    private List<Queue>Previous;

    public void setPrevious(List<Queue> previous) {
        Previous = previous;
    }

    @Override
    public void update(int numOfProduct) {
       for(Queue q:Previous){
           q.setNoofProducts(q.getNoofProducts()+numOfProduct);
       }
    }
}
