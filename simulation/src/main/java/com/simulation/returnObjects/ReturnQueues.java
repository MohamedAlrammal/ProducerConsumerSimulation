package com.simulation.returnObjects;

import com.simulation.Objects.Queue;

import java.util.List;


public class ReturnQueues {
    private List<returnQueue>queues;

    public ReturnQueues(List<returnQueue> queues) {
        this.queues = queues;
    }

    public List<returnQueue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queueList) {
        for(Queue q:queueList){
           this.queues.add(new returnQueue(q.getId(),q.getNoofProducts()));
        }
    }

}
