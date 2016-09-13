package beer.brew.vendingmachine.ui.beer;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.OrderService;
import beer.brew.vendingmachine.data.remote.PaymentProcessor;
import beer.brew.vendingmachine.data.remote.PaymentProcessor.PayStatus;
import beer.brew.vendingmachine.payment.OrderManager;
import beer.brew.vendingmachine.payment.data.Order;
import beer.brew.vendingmachine.payment.data.PayResult;
import rx.Observable;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class BeerInteractor {

    private OrderService orderService;
    private PaymentProcessor paymentProcessor;
    private OrderManager orderManager;

    @Inject
    public BeerInteractor(OrderService orderService, PaymentProcessor paymentProcessor, OrderManager orderManager) {
        this.orderService = orderService;
        this.paymentProcessor = paymentProcessor;
        this.orderManager = orderManager;
    }

    public Observable<PayStatus> pay(Beer beer, final Order.PayType payType) throws Exception {
        Order order = orderManager.generateOrder(beer, payType);
        return orderService.getOrderInfo()
                .flatMap(new Func1<String, Observable<PayStatus>>() {
                    @Override
                    public Observable<PayStatus> call(String orderInfo) {
                        return paymentProcessor.pay(orderInfo, payType);
                    }
                });
    }

    public Observable<PayResult> getPayResult(String orderId) {
        return orderService.getPayResult(orderId)
                .subscribeOn(io())
                .observeOn(mainThread());
    }
}
