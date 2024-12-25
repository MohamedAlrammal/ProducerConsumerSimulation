package com.simulation.QueueObserver;

public interface Subject {
   void addObserverQueue(Observer observer);
   void notifyObservers();
}
