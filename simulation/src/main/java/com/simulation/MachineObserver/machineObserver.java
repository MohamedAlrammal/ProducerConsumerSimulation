package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;
import com.simulation.Objects.Products;

public interface machineObserver {
    void updateState(boolean State, Products products);
    void setMachineList(Machine machineList);
}
