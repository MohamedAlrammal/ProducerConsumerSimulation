package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;

public interface machineObserver {
    void updateState(boolean State);
    void setMachineList(Machine machineList);
}
