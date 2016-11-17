package beer.brew.vendingmachine.ui.beer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.data.model.PayResult;
import beer.brew.vendingmachine.data.model.beer.Beer;
import beer.brew.vendingmachine.data.remote.PayProcessor.PayStatus;
import beer.brew.vendingmachine.ui.base.BaseActivity;
import beer.brew.vendingmachine.ui.widgets.CircleImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeerActivity extends BaseActivity implements BeerView {

    private static final String TAG = BeerActivity.class.getName();

    @BindView(R.id.beer_circle_image_view)
    CircleImageView circleImageView;

    @Inject
    BeerPresenter beerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_beer);

        ButterKnife.bind(this);
        beerPresenter.attachView(this);
        initData();
    }

    private void initData() {
        beerPresenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beerPresenter.detachView();
    }

    @Override
    public void showPayStatus(PayStatus payStatus) {
        Log.i(TAG, "showPayStatus");
    }

    @Override
    public void showPayResult(PayResult payResult) {
        Log.i(TAG, "showPayResult: " + payResult);
    }

    @Override
    public void showBeerInfo(Beer beer) {
        circleImageView.setImageUri(beer.getImageUrls(), true);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getText(R.string.error_msg_field_required), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.action_buy_beer)
    public void onBuyClick() {
        beerPresenter.buyBeer();
    }
}
