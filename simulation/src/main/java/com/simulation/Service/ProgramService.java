package com.simulation.Service;

import com.simulation.Objects.*;
import com.simulation.SnapShot.CareTaker;
import com.simulation.SnapShot.Orginator;
import com.simulation.Threads.GenerateThread;
import com.simulation.returnObjects.ObjectsAnswer;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;
import org.springframework.stereotype.Service;
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
    private final Orginator orginator=new Orginator();
    private final CareTaker careTaker=new CareTaker();
    private GenerateThread generateThread;
    private ObjectsRequest ojectsRequest ;

    public void enterData(ObjectsRequest objectsRequest){
        ObjectsRequest objectsRequest1 = objectsRequest.deepCopy();
        ojectsRequest = objectsRequest.deepCopy();
        if(generateThread!=null)
              generateThread.stopThreads();
        queuess=null;   machiness=null;
        ReturnQueues returnQueues = ReturnQueues.getInstance();
        ReturnMacines returnMacines = ReturnMacines.getInstance();
        returnQueues.setQueues(null);   returnMacines.setMachines(null);
        if(!updates.isEmpty())
            updates.clear();
        queues queues =new queues(objectsRequest1.getQueues());
        machines machines =new machines(objectsRequest1.getMachines());
        for(int i=1;i<=queues.getQueues().getFirst().getNoofProducts();i++)
            queues.getQueues().getFirst().setProduct(new Products(i));
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
           generateThread = new GenerateThread(machiness.getMachines(), queuess.getQueues(), updates, latch);
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

    public void replay(){
        if(generateThread!=null)
            generateThread.stopThreads();
        queuess=null;   machiness=null;
        ReturnQueues returnQueues = ReturnQueues.getInstance();
        ReturnMacines returnMacines = ReturnMacines.getInstance();
        returnQueues.setQueues(null);   returnMacines.setMachines(null);
        orginator.setState(new ObjectsRequest(ojectsRequest.getQueues(),ojectsRequest.getMachines()));
        careTaker.add(orginator.SaveToMemento());
        enterData(careTaker.getMementoList().getState());
    }
    public void stop() {
        if (generateThread != null) {
            generateThread.stopThreads(); // Stop all threads in GenerateThread
            System.out.println("All threads stopped.");
        } else {
            System.out.println("No threads to stop.");
        }
    }
}


