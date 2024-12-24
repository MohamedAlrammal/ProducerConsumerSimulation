package com.simulation.returnObjects;

public class returnQueue {
    private String id;
    private int noOfPruducts;

    public returnQueue(String id, int noOfPruducts) {
        this.id = id;
        this.noOfPruducts = noOfPruducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNoOfPruducts() {
        return noOfPruducts;
    }

    public void setNoOfPruducts(int noOfPruducts) {
        this.noOfPruducts = noOfPruducts;
    }
}
