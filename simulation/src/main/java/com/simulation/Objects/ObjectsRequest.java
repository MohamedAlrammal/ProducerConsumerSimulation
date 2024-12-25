package com.simulation.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectsRequest {
    @JsonProperty("queues")
    private queues queues;
    @JsonProperty("machines")
    private machines machines;

    public ObjectsRequest(queues queues,machines machines) {
        this.queues = queues;
        this.machines = machines;
    }

    public queues getQueues() {
        return queues;
    }

    public void setQueues(queues queues) {
        this.queues = queues;
    }

    public machines getMachines() {
        return machines;
    }

    public void setMachines(machines machines) {
        this.machines = machines;
    }
}
