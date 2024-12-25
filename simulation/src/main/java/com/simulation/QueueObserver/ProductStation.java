package com.simulation.QueueObserver;

import java.util.ArrayList;
import java.util.List;

public class ProductStation implements Subject{
    private List<Observer> ObserverQueues=new ArrayList<>();
    private int numOfProduct;

    @Override
    public void addObserverQueue(Observer observer) {
        ObserverQueues.add(observer);
    }

    @Override
    public void notifyObservers() {
         for(Observer O:ObserverQueues){
             O.update(numOfProduct);
         }
    }
    public void SetNumofProduct(int i){
        this.numOfProduct=i;
        notifyObservers();
    }
}
