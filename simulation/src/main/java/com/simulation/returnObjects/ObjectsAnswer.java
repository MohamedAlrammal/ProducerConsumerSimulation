package com.simulation.returnObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObjectsAnswer {
    private List<returnQueue> queues;
    private List<returnMachine> machines;

    public ObjectsAnswer() {
    }

    public ObjectsAnswer(List<returnQueue> queues, List<returnMachine> machines) {
        this.queues = queues;
        this.machines = machines;
    }

    public List<returnQueue> getQueues() {
        return queues;
    }

    public void setQueues(List<returnQueue> queues) {
        this.queues = queues;
    }

    public List<returnMachine> getMachines() {
        return machines;
    }

    public void setMachines(List<returnMachine> machines) {
        this.machines = machines;
    }

}
