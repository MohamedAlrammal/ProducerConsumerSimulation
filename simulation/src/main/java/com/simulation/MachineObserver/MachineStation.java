package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;

import java.util.ArrayList;
import java.util.List;

public class MachineStation implements MachineSubject{
    private List<machineObserver> machineList=new ArrayList<>();
    private boolean state;

    @Override
    public void addObserverMachine(machineObserver machineObserver) {
        machineList.add(machineObserver);
    }

    @Override
    public void notifyObserver() {
      for(machineObserver m:machineList){
          m.updateState(state);
      }
    }
    public void setState(boolean state) {
        this.state = state;
        notifyObserver();
    }
}
