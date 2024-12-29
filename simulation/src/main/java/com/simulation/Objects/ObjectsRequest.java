package com.simulation.Objects;


import java.util.List;

public class ObjectsRequest {

    private List<Queue> queues;
    private List<Machine> machines;

    public ObjectsRequest(List<Queue> queues, List<Machine> machines) {
        this.queues = queues;
        this.machines = machines;
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}