package beer.brew.vendingmachine.ui.beer;

import beer.brew.vendingmachine.data.remote.PaymentProcessor;
import beer.brew.vendingmachine.payment.data.PayResult;
import beer.brew.vendingmachine.ui.base.MvpView;

public interface BeerView extends MvpView {

    void showPayStatus(PaymentProcessor.PayStatus payStatus);

    void showPayResult(PayResult payResult);

}
