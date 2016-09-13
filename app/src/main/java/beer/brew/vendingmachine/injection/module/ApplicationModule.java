package beer.brew.vendingmachine.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import beer.brew.vendingmachine.data.remote.PaymentService;
import dagger.Module;
import dagger.Provides;

import beer.brew.vendingmachine.data.remote.GitHubService;
import beer.brew.vendingmachine.data.remote.RibotsService;
import beer.brew.vendingmachine.data.remote.SignInService;
import beer.brew.vendingmachine.injection.ApplicationContext;

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
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
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
    PaymentService providePaymentService() {
        return PaymentService.Creator.newPaymentService();
    }
}
