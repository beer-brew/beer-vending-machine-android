package beer.brew.vendingmachine;

import android.app.Application;
import android.content.Context;

import beer.brew.vendingmachine.injection.component.ApplicationComponent;
import beer.brew.vendingmachine.injection.component.DaggerApplicationComponent;
import beer.brew.vendingmachine.injection.module.ApplicationModule;

public class StartupApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static StartupApplication get(Context context) {
        return (StartupApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
