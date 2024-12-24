package com.simulation.Observer;

public interface Subject {
   void addObserverQueue(Observer observer);
   void notifyObservers();
}
