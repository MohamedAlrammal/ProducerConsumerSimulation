package com.simulation.Service;

import com.simulation.Objects.ObjectsRequest;
import com.simulation.Objects.machines;
import com.simulation.Objects.queues;
import com.simulation.Threads.GenerateThread;
import com.simulation.returnObjects.ObjectsAnswer;
import com.simulation.returnObjects.ReturnMacines;
import com.simulation.returnObjects.ReturnQueues;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    public ObjectsAnswer enterData(ObjectsRequest objectsRequest){
        queues queues =new queues(objectsRequest.getQueues().getQueues());
        machines machines =new machines(objectsRequest.getMachines().getMachines());
        ReturnQueues returnQueues =ReturnQueues.getInstance();
        ReturnMacines returnMacines = ReturnMacines.getInstance();
        returnQueues.setQueues(queues.getQueues());
        returnMacines.setMachines(machines.getMachines());
        GenerateThread generateThread=new GenerateThread(machines.getMachines(),queues.getQueues());
        generateThread.createThreads();
        return new ObjectsAnswer(returnQueues,returnMacines);
    }
}
