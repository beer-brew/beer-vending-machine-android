package beer.brew.vendingmachine.ui.beer;

import com.alipay.sdk.app.PayTask;
import java.util.Map;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.PaymentService;
import beer.brew.vendingmachine.payment.OrderManager;
import beer.brew.vendingmachine.payment.data.AlipayStatus;
import beer.brew.vendingmachine.payment.data.Order;
import beer.brew.vendingmachine.payment.data.PayResult;
import beer.brew.vendingmachine.payment.data.PayStatus;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class BeerInteractor {

    private PaymentService paymentService;
    private OrderManager orderManager;

    @Inject
    public BeerInteractor(PaymentService paymentService, OrderManager orderManager) {
        this.paymentService = paymentService;
        this.orderManager = orderManager;
    }

    public Observable<PayStatus> pay(Beer beer, Order.PayType payType) throws Exception {
        Order order = orderManager.generateOrder(beer, payType);
        return paymentService.getOrderInfo(order)
                .flatMap(new Func1<String, Observable<PayStatus>>() {
                    @Override
                    public Observable<PayStatus> call(String orderInfo) {
                        final PayTask payTask = new PayTask(null);
                        return Observable.just(orderInfo)
                                .subscribeOn(io())
                                .map(new Func1<String, PayStatus>() {
                                    @Override
                                    public PayStatus call(String orderInfo) {
                                        Map<String, String> payResult = payTask.payV2(orderInfo, true);
                                        return new AlipayStatus(payResult.get("resultStatus"),
                                                payResult.get("result"),
                                                payResult.get("memo"));
                                    }
                                })
                                .observeOn(mainThread());
                    }
                });
    }

    public Observable<PayResult> getPayResult(String orderId) {
        return paymentService.getPayResult(orderId)
                .subscribeOn(io())
                .observeOn(mainThread());
    }
}
