package beer.brew.vendingmachine;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import beer.brew.vendingmachine.util.DefaultConfig;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, packageName = DefaultConfig.APPLICATION_ID, sdk = DefaultConfig.EMULATE_SDK)
abstract public class ApplicationTestCase {
}