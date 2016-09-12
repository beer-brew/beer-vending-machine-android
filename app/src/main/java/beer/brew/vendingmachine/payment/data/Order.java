package beer.brew.vendingmachine.payment.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import beer.brew.vendingmachine.data.model.Beer;

public class Order implements Serializable {

    private final PayType payType;
    private final String orderID;
    private final String description;
    private final String timestamp;
    private final float price;

    public Order(PayType payType, String orderID, String description, float price, String timestamp) {
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

    public float getPrice() {
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
