package beer.brew.vendingmachine.ui.beer;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.OrderService;
import beer.brew.vendingmachine.data.remote.PayProcessor;
import beer.brew.vendingmachine.data.OrderManager;
import beer.brew.vendingmachine.data.model.PayResult;
import beer.brew.vendingmachine.data.remote.PayProcessor.PayStatus;
import rx.Observable;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class BeerInteractor {

    private OrderService orderService;
    private PayProcessor payProcessor;
    private OrderManager orderManager;

    @Inject
    public BeerInteractor(OrderService orderService, PayProcessor payProcessor, OrderManager orderManager) {
        this.orderService = orderService;
        this.payProcessor = payProcessor;
        this.orderManager = orderManager;
    }

    public Observable<PayStatus> pay(Beer beer) throws Exception {
//        Order order = orderManager.generateOrder(beer, payType);
        return orderService.getOrderInfo()
                .flatMap(new Func1<String, Observable<PayStatus>>() {
                    @Override
                    public Observable<PayStatus> call(String orderInfo) {
                        return payProcessor.pay(orderInfo);
                    }
                });
    }

    public Observable<PayResult> getPayResult(String orderId) {
        return orderService.getPayResult(orderId)
                .subscribeOn(io())
                .observeOn(mainThread());
    }
}
