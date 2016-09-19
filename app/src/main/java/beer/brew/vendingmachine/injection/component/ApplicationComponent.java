package beer.brew.vendingmachine.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import beer.brew.vendingmachine.data.remote.OrderService;
import dagger.Component;
import beer.brew.vendingmachine.data.DataManager;
import beer.brew.vendingmachine.data.SyncService;
import beer.brew.vendingmachine.data.local.DatabaseHelper;
import beer.brew.vendingmachine.data.local.PreferencesHelper;
import beer.brew.vendingmachine.data.remote.GitHubService;
import beer.brew.vendingmachine.data.remote.RibotsService;
import beer.brew.vendingmachine.data.remote.SignInService;
import beer.brew.vendingmachine.injection.ApplicationContext;
import beer.brew.vendingmachine.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    RibotsService ribotsService();
    SignInService signInService();
    GitHubService gitHubService();
    OrderService orderService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
