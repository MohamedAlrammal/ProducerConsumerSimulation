package com.simulation.Threads;

import com.simulation.Objects.Machine;
import com.simulation.Objects.Queue;
import com.simulation.Observer.ObserverClient;

import java.util.List;

public class MachineThread implements Runnable{
    private Machine machine;
    private List<Queue> queues;
    private List<Queue>MoQueues;
    private List<Queue>MoQueues2;
    public MachineThread(Machine machine, List<Queue> queues) {
        this.machine = machine;
        this.queues=queues;
    }

    @Override
    public void run() {
        while(true){
            for(Queue q:queues) {
                if (q.getTo().equals(machine.getId()) && q.getNoofProducts() > 0)
                    this.MoQueues.add(q);
                }
                ObserverClient observerClient =new ObserverClient();
                observerClient.updatePreQueues(MoQueues);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(Queue q:queues) {
                if (q.getFrom().equals(machine.getId()))
                    this.MoQueues2.add(q);
            }
            observerClient.updateFollQueues(MoQueues2);
        }
    }
}
