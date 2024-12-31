package com.simulation.QueueObserver;

import com.simulation.Objects.Products;

public interface Observer {
    void update(int numOfProduct, Products products);
}
