package beer.brew.vendingmachine.ui.beer;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.PaymentProcessor;
import beer.brew.vendingmachine.payment.data.Order;
import beer.brew.vendingmachine.payment.data.PayResult;
import beer.brew.vendingmachine.ui.base.BasePresenter;
import rx.functions.Action1;

public class BeerPresenter extends BasePresenter<BeerView> {

    @Inject
    BeerInteractor beerInteractor;

    @Inject
    public BeerPresenter(BeerInteractor beerInteractor) {
        this.beerInteractor = beerInteractor;
    }

    public void buy(Beer beer, Order.PayType payType) throws Exception {
        beerInteractor.pay(beer, payType)
                .subscribe(new Action1<PaymentProcessor.PayStatus>() {
                    @Override
                    public void call(PaymentProcessor.PayStatus payStatus) {
                        getMvpView().showPayStatus(payStatus);
                    }
                });
    }

    public void getPayResult(String orderId) {
        beerInteractor.getPayResult(orderId)
                .subscribe(new Action1<PayResult>() {
                    @Override
                    public void call(PayResult payResult) {
                        getMvpView().showPayResult(payResult);
                    }
                });
    }

}
