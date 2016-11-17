package beer.brew.vendingmachine.ui.beer;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.PayProcessor;
import beer.brew.vendingmachine.data.model.PayResult;
import beer.brew.vendingmachine.ui.base.BasePresenter;
import rx.functions.Action1;

public class BeerPresenter extends BasePresenter<BeerView> {

    @Inject
    BeerInteractor beerInteractor;

    @Inject
    public BeerPresenter(BeerInteractor beerInteractor) {
        this.beerInteractor = beerInteractor;
    }

    public void buy(Beer beer) throws Exception {
        beerInteractor.pay(beer)
                .subscribe(new Action1<PayProcessor.PayStatus>() {
                    @Override
                    public void call(PayProcessor.PayStatus payStatus) {
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

    public void buyBeer() {
        beerInteractor.getBeer();
    }
}
