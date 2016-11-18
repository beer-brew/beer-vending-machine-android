package beer.brew.vendingmachine.injection.component;

import beer.brew.vendingmachine.injection.PerActivity;
import beer.brew.vendingmachine.injection.module.ActivityModule;
import beer.brew.vendingmachine.ui.beer.BeerActivity;
import beer.brew.vendingmachine.ui.github.GitHubListActivity;
import beer.brew.vendingmachine.ui.signin.SignInActivity;
import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SignInActivity signInActivity);

    void inject(GitHubListActivity gitHubListActivity);

    void inject(BeerActivity beerActivity);

}
