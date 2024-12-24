package com.simulation.Objects;

import java.util.List;

public class Queue {
    private String id;
    private List<String> from;
    private List<String> to;
    private int noofProducts;

    public Queue(String id, List<String> from, List<String> to, int noofProducts) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.noofProducts = noofProducts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getFrom() {
        return from;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public int getNoofProducts() {
        return noofProducts;
    }

    public void setNoofProducts(int noofProducts) {
        this.noofProducts = noofProducts;
    }
}


