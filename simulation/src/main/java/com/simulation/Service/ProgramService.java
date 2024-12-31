package com.simulation.Service;

import com.simulation.Objects.*;
import com.simulation.SnapShot.CareTaker;
import com.simulation.SnapShot.Orginator;
import com.simulation.SnapShot.SnapShotClient;
import com.simulation.Threads.GenerateThread;
import com.simulation.returnObjects.ObjectsAnswer;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class ProgramService {
    private final BlockingQueue<ObjectsAnswer> updates = new LinkedBlockingQueue<>();
    private  queues queuess;
    private  machines machiness;
    private static boolean start=true;
   private Orginator orginator;
   private CareTaker careTaker;
    public void enterData(ObjectsRequest objectsRequest){
        queues queues =new queues(objectsRequest.getQueues());
        machines machines =new machines(objectsRequest.getMachines());
        this.orginator =new Orginator();
        this. careTaker =new CareTaker();
        orginator.setState(objectsRequest);
        careTaker.add(orginator.SaveToMemento());
        Random random = new Random();
        int num = random.nextInt(15);

        for(int i=1;i<=15;i++)
            queues.getQueues().getFirst().setProduct(new Products(i));
        queues.getQueues().getFirst().setNoofProducts(15);
        this.queuess=queues;
        this.machiness=machines;
        start=true;
    }
    private final CountDownLatch latch = new CountDownLatch(1);

    public ObjectsAnswer getUpdates() throws InterruptedException {
        System.out.println("               "+start+"            ");
       if(start) {
           ReturnQueues returnQueues = ReturnQueues.getInstance();
           ReturnMacines returnMacines = ReturnMacines.getInstance();
           returnQueues.setQueues(queuess.getQueues());
           returnMacines.setMachines(machiness.getMachines());
           GenerateThread generateThread = new GenerateThread(machiness.getMachines(), queuess.getQueues(), updates, latch);
           generateThread.createThreads();
           ObjectsAnswer objectsAnswer = new ObjectsAnswer(returnQueues.getQueues(), returnMacines.getMachines());
       } start=false;
        boolean completed = latch.await(10, TimeUnit.SECONDS);
        if (!completed) {
            System.out.println("Threads did not complete in time");
            throw new RuntimeException("No updates available after timeout");
        }

        ObjectsAnswer update = updates.take();
        if (update == null) {
            System.out.println("No updates received after timeout");
            throw new RuntimeException("No updates available after timeout");
        }

        return update;
    }
    public ObjectsAnswer replay(){
        orginator.getStateFromMemento(careTaker.getMementoList(0));
        enterData(orginator.getState());
        try {
            return getUpdates();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


