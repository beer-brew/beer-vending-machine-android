package beer.brew.vendingmachine.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import beer.brew.vendingmachine.StartupApplication;
import beer.brew.vendingmachine.injection.component.ActivityComponent;
import beer.brew.vendingmachine.injection.component.DaggerActivityComponent;
import beer.brew.vendingmachine.injection.module.ActivityModule;

abstract public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(StartupApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}
