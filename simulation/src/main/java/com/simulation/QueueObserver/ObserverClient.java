package com.simulation.QueueObserver;

import com.simulation.Objects.Queue;
import com.simulation.returnObjects.ObjectsAnswer;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ObserverClient {
    public void updatePreQueues(List<Queue> queueList, BlockingQueue<ObjectsAnswer> updatesQueue){
        ProductStation productStation=new ProductStation();
        PreviousQueues previousQueues =new PreviousQueues();
        previousQueues.setPrevious(queueList);
        productStation.addObserverQueue(previousQueues);
        productStation.SetNumofProduct(-1,updatesQueue);
        for(Queue q:queueList){
            System.out.println(q.getId()+" "+q.getNoofProducts()+" ");
        }
    }
    public void updateFollQueues(List<Queue> queueList,BlockingQueue<ObjectsAnswer> updatesQueue){
        ProductStation productStation=new ProductStation();
        FollwersQueues follwersQueues =new FollwersQueues();
        follwersQueues.setFollwer(queueList);
        productStation.addObserverQueue(follwersQueues);
        productStation.SetNumofProduct(1,updatesQueue);
        for(Queue q:queueList){
            System.out.println(q.getId()+" "+q.getNoofProducts()+" ");
        }
    }
}
