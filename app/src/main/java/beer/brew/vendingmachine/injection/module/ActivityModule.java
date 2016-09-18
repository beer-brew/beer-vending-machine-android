package beer.brew.vendingmachine.injection.module;

import android.app.Activity;
import android.content.Context;

import beer.brew.vendingmachine.data.remote.PayProcessor;
import beer.brew.vendingmachine.data.remote.WechatPayProcessor;
import beer.brew.vendingmachine.injection.ActivityContext;
import beer.brew.vendingmachine.data.OrderManager;
import dagger.Module;
import dagger.Provides;

@Module(includes = {AndroidServicesModule.class})
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }

    @Provides
    OrderManager providesPaymentManager() {
        return new OrderManager(mActivity);
    }

    @Provides
    PayProcessor provideWechatPayProcessor() {
        return new WechatPayProcessor(mActivity);
    }

}
