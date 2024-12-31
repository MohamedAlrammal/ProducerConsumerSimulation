package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;
import com.simulation.Objects.Products;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;
import com.simulation.returnObjects.returnMachine;

import java.util.List;

public class StateOfMachine implements machineObserver{
    private Machine machineList;
    ReturnMacines returnMacines =ReturnMacines.getInstance();
    private List<returnMachine>returnMachineList=returnMacines.getMachines();

    public void setMachineList(Machine machineList) {
        this.machineList = machineList;
    }

    @Override
    public void updateState(boolean State, Products products) {
        for(returnMachine r:returnMachineList){
            if(r.getId().equals(machineList.getId())) {
                r.setOn(State);
                r.setProductColor(products.getNumOfProduct());
            }
        }
    }
}
