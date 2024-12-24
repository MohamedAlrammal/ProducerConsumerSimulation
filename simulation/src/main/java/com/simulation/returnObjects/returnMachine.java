package com.simulation.returnObjects;

public class returnMachine {
    private String id;
    private boolean isOn;
    private int productColor;

    public returnMachine(String id, boolean isOn, int productColor) {
        this.id = id;
        this.isOn = isOn;
        this.productColor = productColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public int getProductColor() {
        return productColor;
    }

    public void setProductColor(int productColor) {
        this.productColor = productColor;
    }
}
