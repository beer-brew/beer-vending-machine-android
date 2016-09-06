package beer.brew.vendingmachine.rules;

import android.content.Context;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import beer.brew.vendingmachine.StartupApplication;
import beer.brew.vendingmachine.data.DataManager;
import beer.brew.vendingmachine.injection.component.DaggerTestComponent;
import beer.brew.vendingmachine.injection.component.TestComponent;
import beer.brew.vendingmachine.injection.module.TestAndroidServicesModule;
import beer.brew.vendingmachine.injection.module.TestApplicationModule;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private final TestComponent mTestComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        StartupApplication application = StartupApplication.get(context);
        mTestComponent = DaggerTestComponent.builder()
                .testApplicationModule(new TestApplicationModule(application))
                .testAndroidServicesModule(new TestAndroidServicesModule())
                .build();
    }

    public Context getContext() {
        return mContext;
    }

    public DataManager getMockDataManager() {
        return mTestComponent.dataManager();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                StartupApplication application = StartupApplication.get(mContext);
                application.setComponent(mTestComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
