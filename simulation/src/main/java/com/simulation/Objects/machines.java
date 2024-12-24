package com.simulation.Objects;

import java.util.List;

public class machines {
    private List<Machine> machines;

    public machines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }
}
