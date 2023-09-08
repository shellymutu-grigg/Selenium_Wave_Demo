package com.automation.test.configuration;

import com.automation.test.actions.Get;
import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.TestSetup;

public class BrowserManager extends TestSetup {

    public static void browserSetup() {
        String browserName = Get.globalProperty(ConfigData.BROWSER);
        LocalStore.setObject(ConfigData.SYSTEM_PROPERTY_BROWSER, browserName);

        if (browserName.equalsIgnoreCase(ConfigData.CHROME_DRIVER)) {
            ChromeDriverConfig.chromeDriverSetup();
        } else if (browserName.equalsIgnoreCase(ConfigData.FIREFOX_DRIVER)) {
            FirefoxDriverConfig.firefoxDriverSetup();
        } else if (browserName.equalsIgnoreCase(ConfigData.EDGE_DRIVER)) {
            EdgeDriverConfig.edgeDriverSetup();
        }
    }
}
