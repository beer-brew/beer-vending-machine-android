package beer.brew.vendingmachine.ui.beer;

import javax.inject.Inject;

import com.alipay.sdk.app.PayTask;
import java.util.Map;
import beer.brew.vendingmachine.data.model.AlipayOrder;
import beer.brew.vendingmachine.data.model.AlipayResult;
import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.ui.base.BasePresenter;
import beer.brew.vendingmachine.util.sign.OrderInfoUtil2_0;
import rx.Observable;
import rx.functions.Func1;

import static beer.brew.vendingmachine.data.model.Beer.Size.SMALL;
import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class BeerPresenter extends BasePresenter<BeerView> {

    private static final String ORDER_TIMEOUT = "30m";
    private static final String PRODUCT_CODE = "QUICK_MSECURITY_PAY";

    private static final String APP_ID = "2016072300102649";
    private static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANQdOplHEdXQX7Jh2qZcXu8svhGYv4DfXQlhVioaJy63SJ1YW6lsl2RWaUZ6ADHfN" +
            "/O0jqFr0KJ5zfWl72MddRpQbR/+SWQ//FOp8Fb8y7f4mjMstsliPv8bREYILh68aSc77F3w6GoDp" +
            "/WyUSf4SHhnqmFrcmse7wVusq+6M2xpAgMBAAECgYBRirL+Pa4Djklbn" +
            "/aYQaZVN9QRm7Dy7+sqMfsX0FoDwX3uzwrdxNsmPXnXGAt" +
            "/IJq320+Bipr3+u9MYe" +
            "/3IFnlgeG+Kv87BTBYI2Eb09pmeZYoXJMm3mDD4MhIU9eEjJhCgMKAYHwRkm1VrRTgMtFazvgEQ7oqeCO0N6iYFr+boQJBAP80xQvlPDYVJWQjXWNBTr5CJ2FCq1pAFuiUxIObAQjNt3hkKr6HgwGXNrcWGYVDeD3o9esuisNbEOXSUpbWOF0CQQDUxiS4LIi6WQNGAvWGiXkPId4Ks5bpvVMJczLJu6ewWO5M5ot8toUHe3UV07RdGKf2BuOrXUb7c6OeJUeAZxN9AkEA2EXtEyaiPybueA1sT" +
            "/gBdkTzwH3SXvpfL50baHhMiRxk9lbUzoBiqk9uY" +
            "/wLhp7l4HlzJmpb2DBtgoYZzZAfVQJBAJrwSo7exxXDedHCl93HP6NYSyNJ0WwV1GC+RU6HWr3clHLlMs+27TocmY8yYVWlfbF3JNSFkQnMFGo1xVJoPNUCQBq6bMSRnY7FiyPbSMePt80wu8D9kUWmtR8+HN" +
            "/joLE1WUs2HEh5ohesMgwMWJPNsr+pCl4ppJHx0Uvh2VxAyNk=";

    private Beer beer = new Beer(SMALL);

    @Inject
    public BeerPresenter() {}

    public Observable<AlipayResult> buy(final PayTask payTask) {
        String orderInfo = generateAlipayOrder(beer);
        return Observable.just(orderInfo)
                .subscribeOn(io())
                .map(new Func1<String, AlipayResult>() {
                    @Override
                    public AlipayResult call(String orderInfo) {
                        AlipayResult result = new AlipayResult(payTask.payV2(orderInfo, true));
                        return result;
                    }
                })
                .observeOn(mainThread());
    }

    private String generateAlipayOrder(Beer beer) {
        AlipayOrder order = new AlipayOrder(ORDER_TIMEOUT, PRODUCT_CODE, String.valueOf(beer.cost()), beer.getDescription());
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APP_ID, order.toString());
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
        final String orderInfo = orderParam + "&" + sign;

        return orderInfo;
    }
}
