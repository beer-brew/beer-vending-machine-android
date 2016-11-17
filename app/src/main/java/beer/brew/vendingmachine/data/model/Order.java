package beer.brew.vendingmachine.data.model;

import java.io.Serializable;

public class Order implements Serializable {

    private final PayType payType;
    private final String orderID;
    private final String description;
    private final String timestamp;
    private final String price;

    public Order(PayType payType, String orderID, String description, String price, String timestamp) {
        this.payType = payType;
        this.orderID = orderID;
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
    }

    public PayType getPayType() {
        return payType;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public enum PayType implements Serializable {
        ALIPAY,
        WECHAT_PAY
    }
}
