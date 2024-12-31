package com.simulation.Objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Queue {
    private String id;
    private List<String> from;
    private List<String> to;
    private int noofProducts;
    private LinkedList<Products> product;

    public Queue(String id, List<String> from, List<String> to, int noofProducts,LinkedList<Products>product) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.noofProducts = noofProducts;
        this.product=product;
    }

    public List<Products> getProduct() {
        if (this.product == null) {
            this.product = new LinkedList<>();
        }
          return product;
    }

    public void setProduct(Products products) {
        if (this.product == null) {
            this.product = new LinkedList<>();
        }
        this.product.add(products);
    }
    public void removeProduct(Products products){
        if (this.product == null) {
            this.product = new LinkedList<>();
        }
        this.product.remove(products);
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


