package beer.brew.vendingmachine.ui.main;

import android.os.Bundle;

import javax.inject.Inject;

import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.ui.base.BaseActivity;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

}
