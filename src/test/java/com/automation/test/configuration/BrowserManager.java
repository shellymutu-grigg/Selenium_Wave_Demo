package com.automation.test.configuration;

import com.automation.test.actions.Get;
import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;


@Slf4j
public class BrowserManager {
    static Logger logger = LoggerFactory.getLogger(BrowserManager.class);

    public static void browserSetup(ITestResult result) {
        String browserName = Get.globalProperty(ConfigData.BROWSER);
        LocalStore.setObject(ConfigData.SYSTEM_PROPERTY_BROWSER, browserName);

        if (browserName.equalsIgnoreCase(ConfigData.CHROME_DRIVER)) {
            ChromeDriverConfig.setupChromeDriver();
        } else if (browserName.equalsIgnoreCase(ConfigData.FIREFOX_DRIVER)) {
            FirefoxDriverConfig.setupFirefoxDriver();
        } else if (browserName.equalsIgnoreCase(ConfigData.EDGE_DRIVER)) {
            EdgeDriverConfig.setupEdgeDriver();
        }
        logger.info("Thread {} ({}) has generated Browser", Thread.currentThread().getId(), result.getMethod().getMethodName());
    }
}
