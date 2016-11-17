package beer.brew.vendingmachine.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlipayOrder implements Serializable {
    @SerializedName("timeout_express")
    private final String timeoutExpress;

    @SerializedName("product_code")
    private final String productCode;

    @SerializedName("total_amount")
    private final String totalAmount;

    @SerializedName("body")
    private final String body;

    @SerializedName("out_trade_no")
    private final String outTradeNo;

    public AlipayOrder(String timeoutExpress, String productCode, String totalAmount, String body, String outTradeNo) {
        this.timeoutExpress = timeoutExpress;
        this.productCode = productCode;
        this.totalAmount = totalAmount;
        this.body = body;
        this.outTradeNo = outTradeNo;
    }
}
