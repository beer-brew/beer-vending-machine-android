package beer.brew.vendingmachine.data;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.model.AlipayOrder;
import beer.brew.vendingmachine.data.model.Order;
import util.sign.SignUtils;

public class OrderManager {

    private static final String ALIPAY_ORDER_TIMEOUT = "30m";
    private static final String ALIPAY_PRODUCT_CODE = "QUICK_MSECURITY_PAY";

    private Context context;

    public OrderManager(Context context) {
        this.context = context;
    }

    public Order generateOrder(Beer beer, Order.PayType payType) {
        return new Order(payType,
                generateOutTradeNo(),
                beer.description(),
                calculatePrice(beer),
                generateTimestamp());
    }

    public String generateSignedAlipayOrder(Beer beer) throws Exception {
        AlipayOrder order = new AlipayOrder(
                ALIPAY_ORDER_TIMEOUT,
                ALIPAY_PRODUCT_CODE,
                calculatePrice(beer),
                beer.description(),
                generateOutTradeNo());

        return SignUtils.sign(context, order);
    }

    private String calculatePrice(Beer beer) {
        float price = 0;
        switch (beer.getSize()) {
            case SMALL: price = 3f; break;
            case MIDDLE: price = 4f; break;
            case BIG: price = 5f; break;
            default: break;
        }
        return String.valueOf(price);
    }

    private String generateOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    private String generateTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String timestamp = format.format(date);
        return timestamp;
    }
}
