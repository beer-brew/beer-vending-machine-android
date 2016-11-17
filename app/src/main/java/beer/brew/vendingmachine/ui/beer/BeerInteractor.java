package beer.brew.vendingmachine.ui.beer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.OrderManager;
import beer.brew.vendingmachine.data.model.PayResult;
import beer.brew.vendingmachine.data.model.WechatpayOrder;
import beer.brew.vendingmachine.data.model.beer.Beer;
import beer.brew.vendingmachine.data.model.beer.BeerSize;
import beer.brew.vendingmachine.data.model.beer.BeerType;
import beer.brew.vendingmachine.data.remote.OrderService;
import beer.brew.vendingmachine.data.remote.PayProcessor;
import beer.brew.vendingmachine.data.remote.PayProcessor.PayStatus;
import beer.brew.vendingmachine.util.JsonUtils;
import rx.Observable;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class BeerInteractor {

    interface Callback {
        void onLoadCompleted(Beer beer);

        void onLoadFailed();
    }

    private static final String TAG = "BeerInteractor";

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
                .flatMap(new Func1<WechatpayOrder, Observable<PayStatus>>() {
                    @Override
                    public Observable<PayStatus> call(WechatpayOrder orderInfo) {
                        Log.i(TAG, "orderInfo: " + orderInfo);
                        return payProcessor.pay(JsonUtils.toJson(orderInfo));
                    }
                })
                .subscribeOn(io())
                .observeOn(mainThread());
    }

    public Observable<PayResult> getPayResult(String orderId) {
        return orderService.getPayResult(orderId)
                .subscribeOn(io())
                .observeOn(mainThread());
    }

    public void buyBeer() {

    }

    public void getBeerInfo(Callback callback) {
        List<String> images = new ArrayList<>();
        images.add("http://img.taopic.com/uploads/allimg/140904/235109-140Z40G10569.jpg");
        images.add("http://pic.58pic.com/58pic/13/83/64/67v58PIC7Ze_1024.jpg");
        Beer beer = new Beer.Builder()
                .setPrice(20)
                .setBeerSize(BeerSize.MIDDLE)
                .setImageUrls(images)
                .setType(BeerType.ALE)
                .setDescription("POST MODERN CLASSIC")
                .build();
        callback.onLoadCompleted(beer);
    }
}
