package beer.brew.vendingmachine.ui.beer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import javax.inject.Inject;

import beer.brew.vendingmachine.data.model.Beer;
import beer.brew.vendingmachine.data.remote.PaymentProcessor;
import beer.brew.vendingmachine.payment.data.Order;
import beer.brew.vendingmachine.payment.data.PayResult;
import butterknife.BindView;
import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.ui.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static beer.brew.vendingmachine.data.model.Beer.Size.SMALL;

public class BeerActivity extends BaseActivity implements BeerView {

    private static final String TAG = BeerActivity.class.getSimpleName();

    @Inject
    BeerPresenter beerPresenter;

    @BindView(R.id.action_buy_beer)
    Button buyBeerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_beer);

        ButterKnife.bind(this);
        beerPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beerPresenter.detachView();
    }

    @Override
    public void showPayStatus(PaymentProcessor.PayStatus payStatus) {
        Log.i(TAG, "showPayStatus: " + payStatus.ordinal());
    }

    @Override
    public void showPayResult(PayResult payResult) {
        Log.i(TAG, "showPayResult: " + payResult);
    }

    @OnClick(R.id.action_buy_beer)
    public void onBuyClick() {
        Beer beer = new Beer(SMALL);
        try {
            beerPresenter.buy(beer, Order.PayType.WECHAT_PAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
