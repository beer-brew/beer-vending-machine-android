package beer.brew.vendingmachine.ui.beer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.alipay.sdk.app.PayTask;
import javax.inject.Inject;
import butterknife.BindView;
import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.payment.data.AlipayResult;
import beer.brew.vendingmachine.ui.base.BaseActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beerPresenter.detachView();
    }

    @Override
    public void onPaymentFinished(String payState) {
        Log.i(TAG, "onPaymentFinished: " + payState);
    }

    @Override
    public void onPaymentSuccess() {
        Log.i(TAG, "onPaymentSuccess");
    }

    @Override
    public void onPaymentFailure() {
        Log.i(TAG, "onPaymentFailure");
    }

    @OnClick(R.id.action_buy_beer)
    public void onBuyClick() {
        beerPresenter.buy()
                .subscribe(new Action1<AlipayResult>() {
                    @Override
                    public void call(AlipayResult alipayResult) {
                        onPaymentFinished(alipayResult.getResultStatus());
                    }
                });
    }
}
