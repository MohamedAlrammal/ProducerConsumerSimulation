package com.simulation.returnObjects;

import com.simulation.Objects.Machine;

import java.util.List;

public class ReturnMacines {
   private List<returnMachine> machines;

    public ReturnMacines(List<returnMachine> machines) {
        this.machines = machines;
    }

    public List<returnMachine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machinesList) {
        for(Machine m: machinesList){
            int i=1;
           this.machines.add(new returnMachine(m.getId(),false,i));
           i++;
        }
    }
}
