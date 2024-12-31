package com.simulation.Threads;

import com.simulation.MachineObserver.MachineClient;
import com.simulation.Objects.Machine;
import com.simulation.Objects.Products;
import com.simulation.Objects.Queue;
import com.simulation.QueueObserver.ObserverClient;
import com.simulation.returnObjects.ObjectsAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class MachineThread implements Runnable{
    private Machine machine;
    private List<Queue> queues;
    private List<Queue>MoQueues=new ArrayList<>();
    private List<Queue>MoQueues2=new ArrayList<>();
    private boolean check=false;
    private final CountDownLatch latch ;
    private final BlockingQueue<ObjectsAnswer> updatesQueue;
    private Products products;
    public MachineThread(Machine machine, List<Queue> queues, BlockingQueue<ObjectsAnswer> updatesQueue, CountDownLatch latch) {
        this.machine = machine;
        this.queues=queues;
        this.updatesQueue = updatesQueue;
        this.latch=latch;
    }

    @Override
    public void run() {

        while(true){

            for(Queue q:queues) {
               for(int i=0;i<q.getTo().size();i++) {
                   if (q.getTo().get(i).equals(machine.getId()) && q.getNoofProducts() > 0 && i == q.getTo().size() - 1) {
                       System.out.println(q.getTo().get(i));
                       MoQueues.add(q);
                      this.products=q.getProduct().getFirst();
                       this.check = true;
                       System.out.println("previous queues done");
                   }
                   else if(q.getTo().get(i).equals(machine.getId()) && q.getNoofProducts() > 0) {
                       this.check = true;
                       this.products=q.getProduct().getFirst();
                   }
               }
            }
            if(check) {

                ObserverClient observerClient = new ObserverClient();
                observerClient.updatePreQueues(MoQueues,updatesQueue,products);
                latch.countDown();
                MachineClient machineClient =new MachineClient();
                machineClient.UpdateStateMachines(machine,true,updatesQueue,products);
                latch.countDown();
                MoQueues.clear();
                System.out.println("machine done");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } latch.countDown();

                for (Queue q : queues) {
                    for(int i=0;i<q.getFrom().size();i++)
                      if (q.getFrom().get(i).equals(machine.getId())) {
                        this.MoQueues2.add(q);
                          System.out.println("fow Queues done");
                    }

                } machineClient.UpdateStateMachines(machine,false,updatesQueue,products);
                observerClient.updateFollQueues(MoQueues2,updatesQueue,products);
                latch.countDown();
                MoQueues2.clear();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }check= false;
        }
    }
}
