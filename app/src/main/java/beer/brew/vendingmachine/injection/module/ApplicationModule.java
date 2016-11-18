package beer.brew.vendingmachine.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import beer.brew.vendingmachine.data.remote.GitHubService;
import beer.brew.vendingmachine.data.remote.OrderService;
import beer.brew.vendingmachine.data.remote.SignInService;
import beer.brew.vendingmachine.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    SignInService provideSignInService() {
        return SignInService.Creator.newSignInService();
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService() {
        return GitHubService.Creator.newGitHubService();
    }

    @Provides
    @Singleton
    OrderService provideOrderService() {
        return OrderService.Creator.newOrderService();
    }
}
