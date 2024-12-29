package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;
import com.simulation.returnObjects.ObjectsAnswer;

import java.util.concurrent.BlockingQueue;

public class MachineClient {
    public void UpdateStateMachines(Machine machine, boolean state, BlockingQueue<ObjectsAnswer> updatesQueue){
        MachineStation machineSubject =new MachineStation();
        machineObserver machineObserver=new StateOfMachine();
        machineObserver.setMachineList(machine);
        machineSubject.addObserverMachine(machineObserver);
        machineSubject.setState(state, updatesQueue);
        System.out.println(machine.getId()+" "+state);
    }
}
