package com.simulation.Objects;

import java.util.ArrayList;
import java.util.List;

public class Machine {
    private String id;
    private List<String> from;
    private List<String> to;

    public Machine(String id, List<String> from, List<String> to) {
        this.id = id;
        this.from = from;
        this.to = to;
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
    public Machine deepCopy() {
        // Create a new instance with a deep copy of 'from' and 'to' lists
        List<String> copiedFrom = new ArrayList<>(from);
        List<String> copiedTo = new ArrayList<>(to);
        return new Machine(id, copiedFrom, copiedTo);
    }
}
