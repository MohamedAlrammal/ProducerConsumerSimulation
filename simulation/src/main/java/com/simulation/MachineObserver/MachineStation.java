package com.simulation.MachineObserver;

import com.simulation.Objects.Machine;
import com.simulation.Objects.Products;
import com.simulation.returnObjects.ObjectsAnswer;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;
import org.springframework.beans.factory.parsing.ParseState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MachineStation implements MachineSubject{
    private List<machineObserver> machineList=new ArrayList<>();
    private boolean state;
    private Products products;

    @Override
    public void addObserverMachine(machineObserver machineObserver) {
        machineList.add(machineObserver);
    }

    @Override
    public void notifyObserver() {
      for(machineObserver m:machineList){
          m.updateState(state,products);
      }
    }
    public void setState(boolean state, BlockingQueue<ObjectsAnswer> updatesQueue, Products products) {
        this.state = state;
        this.products=products;
        notifyObserver();
        ReturnQueues returnQueues=ReturnQueues.getInstance();
        ReturnMacines returnMacines =ReturnMacines.getInstance();
        try {
            updatesQueue.put(new ObjectsAnswer(returnQueues.getQueues(),returnMacines.getMachines()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
