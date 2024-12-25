package com.simulation.Controller;



import com.simulation.Objects.ObjectsRequest;
import com.simulation.Service.ProgramService;
import com.simulation.returnObjects.ObjectsAnswer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final ProgramService programService;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(ProgramService programService, SimpMessagingTemplate messagingTemplate) {
        this.programService = programService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/enterData") // Maps messages sent to /app/enterData
    @SendTo("/topic/response") // Sends the response to /topic/response
    public ObjectsAnswer enterData(ObjectsRequest objectsRequest) {
        return programService.enterData(objectsRequest);
    }
}
