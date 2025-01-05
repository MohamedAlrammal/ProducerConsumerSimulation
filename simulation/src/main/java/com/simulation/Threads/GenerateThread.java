package com.simulation.Threads;

import com.simulation.Objects.Machine;
import com.simulation.Objects.Queue;
import com.simulation.returnObjects.ObjectsAnswer;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;


public class GenerateThread {
    private List<Machine> machines;
    private List<Queue> queues;
    private MachineThread[] tasks ;
    private Thread[] threads;
    private final CountDownLatch latch ;
    private final BlockingQueue<ObjectsAnswer> updatesQueue;
    public GenerateThread(List<Machine> machines, List<Queue> queues, BlockingQueue<ObjectsAnswer> updatesQueue,CountDownLatch latch ) {
        this.machines = machines;
        this.queues = queues;
        this.threads=new Thread[machines.size()];
        this.tasks=new MachineThread[machines.size()];
        this.updatesQueue = updatesQueue;
        this.latch=latch;
    }
    public void createThreads(){
        for(int i=0;i< machines.size();i++){
            tasks[i]=new MachineThread(machines.get(i), queues,updatesQueue,latch);
            threads[i]= new Thread(tasks[i]);
            System.out.println("create is ok");
        }
        for (Thread thread : threads) {
            System.out.println("yes thread");
            thread.start();

        }
    }
    public void stopThreads() {
        for (MachineThread task : tasks) {
            task.stopThread();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interruption during stop: " + e.getMessage());
            }
        }
        System.out.println("All threads stopped.");
    }

}

