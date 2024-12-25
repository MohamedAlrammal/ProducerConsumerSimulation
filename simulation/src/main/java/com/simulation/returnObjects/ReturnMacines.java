package com.simulation.returnObjects;

import com.simulation.Objects.Machine;

import java.util.ArrayList;
import java.util.List;

public class ReturnMacines {
    private static ReturnMacines instance; // Singleton instance
    private List<returnMachine> machines;

    // Private constructor to prevent instantiation
    private ReturnMacines() {
        this.machines = new ArrayList<>();
    }

    // Public method to get the singleton instance
    public static synchronized ReturnMacines getInstance() {
        if (instance == null) {
            instance = new ReturnMacines();
        }
        return instance;
    }

    public List<returnMachine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machinesList) {
        machines.clear(); // Clear existing machines to avoid duplicates
        int i = 1;
        for (Machine m : machinesList) {
            this.machines.add(new returnMachine(m.getId(), false, i));
            i++;
        }
    }
}
