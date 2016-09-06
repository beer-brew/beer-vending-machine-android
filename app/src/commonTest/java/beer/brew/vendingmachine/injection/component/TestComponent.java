package beer.brew.vendingmachine.injection.component;

import beer.brew.vendingmachine.injection.module.TestApplicationModule;
import beer.brew.vendingmachine.injection.module.TestAndroidServicesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestApplicationModule.class, TestAndroidServicesModule.class})
public interface TestComponent extends ApplicationComponent {

}
