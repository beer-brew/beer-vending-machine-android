package beer.brew.vendingmachine.data.model;

import java.io.Serializable;

import beer.brew.vendingmachine.data.model.beer.Beer;

public class Order implements Serializable {

    private final String orderID;
    private final String description;
    private final String timestamp;
    private final Beer beer;

    public Order(String orderID, String description, String timestamp, Beer beer) {
        this.orderID = orderID;
        this.description = description;
        this.timestamp = timestamp;
        this.beer = beer;
    }
}
