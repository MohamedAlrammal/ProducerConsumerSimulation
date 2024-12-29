package com.simulation.QueueObserver;

import com.simulation.returnObjects.ObjectsAnswer;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ProductStation implements Subject {
    private List<Observer> ObserverQueues = new ArrayList<>();
    private int numOfProduct;

    @Override
    public void addObserverQueue(Observer observer) {
        ObserverQueues.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer O : ObserverQueues) {
            O.update(numOfProduct);
        }
    }

    public void SetNumofProduct(int i, BlockingQueue<ObjectsAnswer> updatesQueue) {
        if (ObserverQueues.isEmpty()) {
            System.err.println("ObserverQueues is empty. Skipping notification.");
            return; // Avoid accessing an empty list
        }
        this.numOfProduct = i;
        notifyObservers(); // Notify only if the list is not empty
        ReturnQueues returnQueues = ReturnQueues.getInstance();
        ReturnMacines returnMacines = ReturnMacines.getInstance();
        try {
            updatesQueue.put(new ObjectsAnswer(returnQueues.getQueues(), returnMacines.getMachines()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Failed to add update to queue: " + e.getMessage());
        }
    }
}
