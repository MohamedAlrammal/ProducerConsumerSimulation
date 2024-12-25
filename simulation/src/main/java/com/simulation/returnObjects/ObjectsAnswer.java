package com.simulation.returnObjects;

public class ObjectsAnswer {
    private ReturnQueues returnQueues;
    private ReturnMacines returnMacines;

    public ObjectsAnswer(ReturnQueues returnQueues, ReturnMacines returnMacines) {
        this.returnQueues = returnQueues;
        this.returnMacines = returnMacines;
    }

    public ReturnQueues getReturnQueues() {
        return returnQueues;
    }

    public void setReturnQueues(ReturnQueues returnQueues) {
        this.returnQueues = returnQueues;
    }

    public ReturnMacines getReturnMacines() {
        return returnMacines;
    }

    public void setReturnMacines(ReturnMacines returnMacines) {
        this.returnMacines = returnMacines;
    }
}
