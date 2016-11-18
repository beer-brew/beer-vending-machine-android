package beer.brew.vendingmachine.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import beer.brew.vendingmachine.data.local.DatabaseHelper;
import beer.brew.vendingmachine.data.local.PreferencesHelper;
import beer.brew.vendingmachine.data.remote.GitHubService;
import beer.brew.vendingmachine.data.remote.OrderService;
import beer.brew.vendingmachine.data.remote.SignInService;
import beer.brew.vendingmachine.injection.ApplicationContext;
import beer.brew.vendingmachine.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    SignInService signInService();
    GitHubService gitHubService();
    OrderService orderService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    Bus eventBus();

}
