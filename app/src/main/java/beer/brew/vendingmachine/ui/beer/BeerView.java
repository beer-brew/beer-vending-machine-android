package beer.brew.vendingmachine.ui.beer;

import java.util.List;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.ui.base.MvpView;

public interface BeerView extends MvpView {

    void showBeers(List<Beer> beers);
    void showBeersEmpty();
    void showError();

}
