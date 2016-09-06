package beer.brew.vendingmachine;

import beer.brew.vendingmachine.util.DefaultConfig;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, packageName = DefaultConfig.APPLICATION_ID, sdk = DefaultConfig.EMULATE_SDK)
abstract public class ApplicationTestCase {
}