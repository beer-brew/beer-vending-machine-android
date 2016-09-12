package beer.brew.vendingmachine.payment;

import android.app.Activity;
import android.content.Context;

import com.alipay.sdk.app.PayTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.payment.data.AlipayOrder;
import beer.brew.vendingmachine.payment.data.AlipayResult;
import rx.Observable;
import rx.functions.Func1;
import util.sign.SignUtils;

import static beer.brew.vendingmachine.data.model.Beer.Size.SMALL;
import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class PaymentManager {

    private static final String ALIPAY_ORDER_TIMEOUT = "30m";
    private static final String ALIPAY_PRODUCT_CODE = "QUICK_MSECURITY_PAY";

    private Context context;

    public PaymentManager(Context context) {
        this.context = context;
    }

    public Observable<AlipayResult> pay() {
        try {
            String orderInfo = generateSignedAlipayOrder(new Beer(SMALL));
            final PayTask payTask = new PayTask((Activity) context);
            return Observable.just(orderInfo)
                    .subscribeOn(io())
                    .map(new Func1<String, AlipayResult>() {
                        @Override
                        public AlipayResult call(String orderInfo) {
                            Map<String, String> payResult = payTask.payV2(orderInfo, true);
                            AlipayResult result = new AlipayResult(
                                    payResult.get("resultStatus"),
                                    payResult.get("result"),
                                    payResult.get("memo"));
                            return result;
                        }
                    })
                    .observeOn(mainThread());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Observable.empty();
    }

    private String generateSignedAlipayOrder(Beer beer) throws Exception {
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
            case SMALL: price = (float) 3.0; break;
            case MIDDLE: price = (float) 4.0; break;
            case BIG: price = (float) 5.0; break;
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
}
