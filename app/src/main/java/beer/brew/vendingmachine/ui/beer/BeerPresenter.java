package beer.brew.vendingmachine.ui.beer;

import java.util.List;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.ui.base.BasePresenter;

public class BeerPresenter extends BasePresenter<BeerView> implements BeerInteractor.Callback {

    private BeerInteractor interactor;

    @Inject
    public BeerPresenter(BeerInteractor interactor) {
        this.interactor = interactor;
    }

    public void loadBeers() {
        interactor.load(this);
    }

    @Override
    public void onLoadBeersCompleted(List<Beer> beers) {
        if (beers == null || beers.isEmpty()) {
            getMvpView().showBeersEmpty();
        } else {
            getMvpView().showBeers(beers);
        }
    }

    @Override
    public void onLoadBeersFailed(Throwable e) {
        getMvpView().showError();
    }
}
