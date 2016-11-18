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

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class TestApplicationModule {

    private final Application mApplication;

    public TestApplicationModule(Application application) {
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

    /*************
     * MOCKS
     *************/

    @Provides
    @Singleton
    SignInService provideSignInService() {
        return mock(SignInService.class);
    }

    @Provides
    @Singleton
    GitHubService provideGitHubService() {
        return mock(GitHubService.class);
    }

    @Provides
    @Singleton
    OrderService provideOrderService() {
        return OrderService.Creator.newOrderService();
    }
}
