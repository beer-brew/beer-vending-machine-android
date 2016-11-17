package beer.brew.vendingmachine.ui.beer;

import beer.brew.vendingmachine.data.model.beer.Beer;
import beer.brew.vendingmachine.data.model.PayResult;
import beer.brew.vendingmachine.data.remote.PayProcessor.PayStatus;
import beer.brew.vendingmachine.ui.base.MvpView;

public interface BeerView extends MvpView {

    void showPayStatus(PayStatus payStatus);

    void showPayResult(PayResult payResult);

    void showBeerInfo(Beer beer);

    void showError();
}
