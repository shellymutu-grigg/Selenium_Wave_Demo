package com.automation.test.configuration;

import com.tidal.wave.browser.Browser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.automation.test.actions.Get;
import com.automation.test.data.ConfigData;
import com.automation.test.data.LocalStore;
import com.automation.test.TestSetup;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BrowserManager extends TestSetup {

    public static WebDriver browserSetup() {
        String browserName;
        WebDriver webDriver = null;
        browserName = Get.globalProperty(ConfigData.BROWSER);
        LocalStore.setObject(ConfigData.SYSTEM_PROPERTY_BROWSER, browserName);

        if (browserName.equalsIgnoreCase(ConfigData.CHROME_DRIVER)) {
            Browser.withOptions(ChromeDriverConfig.chromeOptions())
                    .withWaitTime(Duration.ofSeconds(15))
                    .open(Get.url());
            webDriver = new ChromeDriver(ChromeDriverConfig.chromeOptions());
        } else if (browserName.equalsIgnoreCase(ConfigData.FIREFOX_DRIVER)) {
            Browser.withOptions(FirefoxDriverConfig.firefoxOptions())
                    .withWaitTime(Duration.ofSeconds(15))
                    .open(Get.url());
            webDriver = new FirefoxDriver(FirefoxDriverConfig.firefoxOptions());
        } else if (browserName.equalsIgnoreCase(ConfigData.EDGE_DRIVER)) {
            Browser.withOptions(EdgeDriverConfig.edgeOptions())
                    .withWaitTime(Duration.ofSeconds(15))
                    .open(Get.url());
            webDriver = new EdgeDriver(EdgeDriverConfig.edgeOptions());
        }
        //Set the webDriver in the threadLocalDriver
        TestSetup.threadLocalDriver.set(webDriver);

        TestSetup.getDriver().manage().window().setSize(new Dimension(1050, 650));

        assert webDriver != null;

        LocalStore.setObject(ConfigData.SYSTEM_PROPERTY_WEBDRIVER, webDriver);
        return webDriver;
    }
}
