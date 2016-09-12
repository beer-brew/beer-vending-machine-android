package beer.brew.vendingmachine.ui.beer;

import javax.inject.Inject;

import beer.brew.vendingmachine.payment.data.AlipayResult;
import beer.brew.vendingmachine.ui.base.BasePresenter;
import beer.brew.vendingmachine.payment.PaymentManager;
import rx.Observable;

public class BeerPresenter extends BasePresenter<BeerView> {

    @Inject
    PaymentManager paymentManager;

    @Inject
    public BeerPresenter(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    public Observable<AlipayResult> buy() {
        return paymentManager.pay();
    }
}
