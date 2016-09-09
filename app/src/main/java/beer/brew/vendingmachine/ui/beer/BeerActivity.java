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

    @Inject
    BeerPresenter beerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);

        beerPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beerPresenter.detachView();
    }
}
