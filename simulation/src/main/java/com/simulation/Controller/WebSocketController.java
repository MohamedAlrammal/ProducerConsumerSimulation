package com.simulation.Controller;



import com.simulation.Objects.ObjectsRequest;
import com.simulation.Service.ProgramService;
import com.simulation.Threads.MachineThread;
import com.simulation.returnObjects.ObjectsAnswer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/producer")
@CrossOrigin(origins = "http://localhost:3000")
public class WebSocketController {
    @Autowired
    private ProgramService programService;
    @PostMapping("/enter")
    public void enterData(@RequestBody ObjectsRequest objectsRequest) {

        programService.enterData(objectsRequest);
    }
    @GetMapping("/start")
    public ObjectsAnswer getUpdates() throws InterruptedException {
        ObjectsAnswer update = programService.getUpdates();
        if (update == null) {
            throw new RuntimeException("No updates available after timeout");
            // Or return some default response
        }
        return update;
    }
    @PostMapping("/replay")
    public void replay(){
        programService.replay();
    }
}