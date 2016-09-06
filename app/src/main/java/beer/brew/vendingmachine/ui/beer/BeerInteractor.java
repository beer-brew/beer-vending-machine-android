package beer.brew.vendingmachine.ui.beer;

import java.util.List;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.BeersService;
import rx.Observer;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class BeerInteractor {

    private BeersService service;

    interface Callback {
        void onLoadBeersCompleted(List<Beer> beers);
        void onLoadBeersFailed(Throwable e);
    }

    @Inject
    public BeerInteractor(BeersService service) {
        this.service = service;
    }

    public void load(final Callback callback) {
        service.getBeers().subscribeOn(io()).observeOn(mainThread()).subscribe(new Observer<List<Beer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onLoadBeersFailed(e);
            }

            @Override
            public void onNext(List<Beer> beers) {
                callback.onLoadBeersCompleted(beers);
            }
        });
    }
}
