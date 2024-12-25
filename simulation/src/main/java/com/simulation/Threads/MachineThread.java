package com.simulation.Threads;

import com.simulation.MachineObserver.MachineClient;
import com.simulation.Objects.Machine;
import com.simulation.Objects.Queue;
import com.simulation.QueueObserver.ObserverClient;

import java.util.List;

public class MachineThread implements Runnable{
    private Machine machine;
    private List<Queue> queues;
    private List<Queue>MoQueues;
    private List<Queue>MoQueues2;
    private boolean check=false;
    public MachineThread(Machine machine, List<Queue> queues) {
        this.machine = machine;
        this.queues=queues;
    }



    @Override
    public void run() {
        while(true){
            for(Queue q:queues) {
               for(int i=0;i<q.getTo().size();i++)
                  if (q.getTo().get(i).equals(machine.getId()) && q.getNoofProducts() > 0) {
                    this.MoQueues.add(q);
                    q.setNoofProducts(q.getNoofProducts()-1);
                    this.check = true;
                }
                }
            if(check) {
                ObserverClient observerClient = new ObserverClient();
                observerClient.updatePreQueues(MoQueues);
                MachineClient machineClient =new MachineClient();
                machineClient.UpdateStateMachines(machine,true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (Queue q : queues) {
                    for(int i=0;i<q.getFrom().size();i++)
                      if (q.getFrom().get(i).equals(machine.getId())) {
                        this.MoQueues2.add(q);
                        q.setNoofProducts(q.getNoofProducts() + 1);
                    }
                } machineClient.UpdateStateMachines(machine,false);
                observerClient.updateFollQueues(MoQueues2);
            }check= false;
        }
    }
}
