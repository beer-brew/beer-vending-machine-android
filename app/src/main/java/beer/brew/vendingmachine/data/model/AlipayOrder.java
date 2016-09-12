package beer.brew.vendingmachine.data.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

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

    public AlipayOrder(String timeoutExpress, String productCode, String totalAmount, String body) {
        this.timeoutExpress = timeoutExpress;
        this.productCode = productCode;
        this.totalAmount = totalAmount;
        this.body = body;
        this.outTradeNo = getOutTradeNo();
    }

    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
