package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;

public class MachineClient {
    public void UpdateStateMachines(Machine machine,boolean state){
        MachineStation machineSubject =new MachineStation();
        machineObserver machineObserver=new StateOfMachine();
        machineObserver.setMachineList(machine);
        machineSubject.addObserverMachine(machineObserver);
        machineSubject.setState(state);
    }
}
