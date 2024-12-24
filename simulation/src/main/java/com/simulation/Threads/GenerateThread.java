package com.simulation.Threads;

import com.simulation.Objects.Machine;
import com.simulation.Objects.Queue;

import java.util.List;


public class GenerateThread {
    private List<Machine> machines;
    private List<Queue> queues;
    private MachineThread[] tasks ;
    private Thread[] threads;
    public GenerateThread(List<Machine> machines, List<Queue> queues) {
        this.machines = machines;
        this.queues = queues;
        this.threads=new Thread[machines.size()];
        this.tasks=new MachineThread[machines.size()];
    }
    public void createThreads(){
        for(int i=0;i< machines.size();i++){
            tasks[i]=new MachineThread(machines.get(i), queues);
            threads[i]= new Thread(tasks[i]);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

}

