package beer.brew.vendingmachine.ui.beer;

import beer.brew.vendingmachine.ui.base.MvpView;

public interface BeerView extends MvpView {

    void onPaymentFinished(String payState);

    void onPaymentSuccess();

    void onPaymentFailure();
}
