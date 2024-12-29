package com.simulation.returnObjects;

import com.simulation.Objects.Queue;

import java.util.ArrayList;
import java.util.List;

public class ReturnQueues {
    private static ReturnQueues instance; // Singleton instance
    private List<returnQueue> queues;

    // Private constructor to prevent external instantiation
    private ReturnQueues() {
        this.queues = new ArrayList<>();
    }

    // Public method to get the Singleton instance
    public static synchronized ReturnQueues getInstance() {
        if (instance == null) {
            instance = new ReturnQueues();
        }
        return instance;
    }

    public List<returnQueue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queueList) {

        for (Queue q : queueList) {
            this.queues.add(new returnQueue(q.getId(), q.getNoofProducts()));
        }
    }
}

