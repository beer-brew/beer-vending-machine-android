package beer.brew.vendingmachine.ui.beer;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerActivity extends BaseActivity implements BeerView {

    @BindView(R.id.beer_recycler_view)
    RecyclerView beerRecyclerView;

    @Inject
    BeerPresenter beerPresenter;

    private BeerAdapter beerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);

        beerPresenter.attachView(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beerPresenter.detachView();
    }

    @Override
    public void showBeers(List<Beer> beers) {
        beerAdapter.setBeers(beers);
    }

    @Override
    public void showBeersEmpty() {

    }

    @Override
    public void showError() {

    }

    private void initView() {
        beerAdapter = new BeerAdapter(this);
        beerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        beerRecyclerView.setAdapter(beerAdapter);
    }
}
