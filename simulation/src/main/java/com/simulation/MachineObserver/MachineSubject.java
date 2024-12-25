package com.simulation.MachineObserver;

public interface MachineSubject {
    void addObserverMachine(machineObserver machineObserver);
    void notifyObserver();
}
